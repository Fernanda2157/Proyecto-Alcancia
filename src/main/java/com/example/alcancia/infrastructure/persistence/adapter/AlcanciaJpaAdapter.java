package com.example.alcancia.infrastructure.persistence.adapter;

import com.example.alcancia.domain.model.Alcancia;
import com.example.alcancia.domain.model.EstadoAlcancia;
import com.example.alcancia.domain.repository.AlcanciaRepository;
import com.example.alcancia.infrastructure.persistence.entity.AlcanciaJpaEntity;
import com.example.alcancia.infrastructure.persistence.repository.SpringAlcanciaJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Puente entre el Dominio rico y JPA
@Component
public class AlcanciaJpaAdapter implements AlcanciaRepository {

    private final SpringAlcanciaJpaRepository jpa;

    public AlcanciaJpaAdapter(SpringAlcanciaJpaRepository jpa) { this.jpa = jpa; }

    @Override
    public Alcancia guardar(Alcancia a) {
        return toDomain(jpa.save(toJpa(a)));
    }

    @Override
    public Optional<Alcancia> buscarPorId(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Alcancia> buscarTodas() {
        return jpa.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    private AlcanciaJpaEntity toJpa(Alcancia a) {
        AlcanciaJpaEntity e = new AlcanciaJpaEntity(
                a.getNombreAhorrista(), a.getSaldo(), a.getMeta(), a.getEstado().name());
        e.setId(a.getId());
        return e;
    }

    private Alcancia toDomain(AlcanciaJpaEntity e) {
        return new Alcancia(e.getId(), e.getNombreAhorrista(),
                e.getSaldo(), e.getMeta(), EstadoAlcancia.valueOf(e.getEstado()));
    }
}
