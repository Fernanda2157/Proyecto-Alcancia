package com.example.alcancia.service;

import com.example.alcancia.entity.AlcanciaEntity;
import com.example.alcancia.repository.AlcanciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// CAPA: Service — lógica de negocio profesional
@Service
public class AlcanciaService {

    @Autowired
    private AlcanciaRepository repository;

    // Tolerancia para comparaciones matemáticas con Double
    private static final double EPSILON = 0.001;

    public List<AlcanciaEntity> listarTodas() {
        return repository.findAll();
    }

    public AlcanciaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe alcancía con ID: " + id));
    }

    public void crearAlcancia(String nombre, Double meta) {
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (meta == null || meta <= 0)
            throw new IllegalArgumentException("La meta debe ser mayor a cero");
        
        repository.save(new AlcanciaEntity(nombre, meta));
    }

    public String depositar(Long id, Double monto) {
        // Validación de entrada
        if (monto == null || monto <= 0)
            throw new IllegalArgumentException("El monto debe ser mayor a cero");

        AlcanciaEntity entity = buscarPorId(id);

        // Validación de estado de la alcancía
        if (entity.isCompletada())
            throw new IllegalStateException("La alcancía ya está COMPLETADA");

        // Regla estricta: No exceder la meta (usando EPSILON para precisión)
        if (monto > (entity.getFaltante() + EPSILON)) {
            throw new IllegalArgumentException("El monto $" + String.format("%.2f", monto) 
                + " excede el límite. Solo faltan $" + String.format("%.2f", entity.getFaltante()) 
                + " para alcanzar la meta.");
        }

        // Ejecución de la lógica de negocio
        double nuevoSaldo = entity.getSaldo() + monto;
        entity.setSaldo(nuevoSaldo);

        // Si el faltante es menor a la tolerancia, la alcancía se completa
        if (entity.getFaltante() < EPSILON) {
            entity.setEstado("COMPLETADA");
        }

        repository.save(entity);

        return entity.isCompletada()
            ? "¡Meta alcanzada! Alcancía COMPLETADA."
            : "Depósito exitoso. Falta: $" + String.format("%.2f", entity.getFaltante());
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. No existe la alcancía con ID: " + id);
        }
        repository.deleteById(id);
    }
}