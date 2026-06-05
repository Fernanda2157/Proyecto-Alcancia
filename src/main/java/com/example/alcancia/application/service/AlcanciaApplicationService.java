package com.example.alcancia.application.service;

import com.example.alcancia.application.dto.AlcanciaResponse;
import com.example.alcancia.application.dto.CrearAlcanciaCommand;
import com.example.alcancia.domain.exception.AlcanciaException;
import com.example.alcancia.domain.model.Alcancia;
import com.example.alcancia.domain.model.Monto;
import com.example.alcancia.domain.repository.AlcanciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Orquestador delgado — NO contiene reglas de negocio
@Service
public class AlcanciaApplicationService {

    private final AlcanciaRepository repository;

    public AlcanciaApplicationService(AlcanciaRepository repository) {
        this.repository = repository;
    }

    public List<AlcanciaResponse> listarTodas() {
        return repository.buscarTodas().stream()
                .map(a -> new AlcanciaResponse(a, ""))
                .collect(Collectors.toList());
    }

    public AlcanciaResponse buscarPorId(Long id) {
        Alcancia a = repository.buscarPorId(id)
                .orElseThrow(() -> new AlcanciaException("No existe alcancía con ID: " + id));
        return new AlcanciaResponse(a, "");
    }

    public AlcanciaResponse crearAlcancia(CrearAlcanciaCommand cmd) {
        // El constructor del Agregado valida nombre y meta
        Alcancia a = new Alcancia(cmd.getNombreAhorrista(), cmd.getMeta());
        return new AlcanciaResponse(repository.guardar(a), "Alcancía creada exitosamente");
    }

    public AlcanciaResponse depositar(Long id, double monto) {
        // 1. Cargar el Agregado
        Alcancia a = repository.buscarPorId(id)
                .orElseThrow(() -> new AlcanciaException("No existe alcancía con ID: " + id));
        // 2. Monto valida Regla 1 (monto > 0)
        // 3. El Agregado aplica Reglas 2 y 3
        a.depositar(new Monto(monto));
        // 4. Persistir
        Alcancia guardada = repository.guardar(a);
        String msg = guardada.estaCompletada()
                ? "¡Felicidades " + guardada.getNombreAhorrista() + "! Meta alcanzada."
                : "Depósito exitoso. Falta: $" + guardada.getFaltante();
        return new AlcanciaResponse(guardada, msg);
    }

    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
