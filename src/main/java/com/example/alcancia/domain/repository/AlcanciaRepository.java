package com.example.alcancia.domain.repository;

import com.example.alcancia.domain.model.Alcancia;
import java.util.List;
import java.util.Optional;


public interface AlcanciaRepository {
    Alcancia guardar(Alcancia alcancia);
    Optional<Alcancia> buscarPorId(Long id);
    List<Alcancia> buscarTodas();
    void eliminar(Long id);
}
