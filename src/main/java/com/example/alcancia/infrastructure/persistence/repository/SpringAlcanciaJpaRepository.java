package com.example.alcancia.infrastructure.persistence.repository;

import com.example.alcancia.infrastructure.persistence.entity.AlcanciaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringAlcanciaJpaRepository extends JpaRepository<AlcanciaJpaEntity, Long> {}
