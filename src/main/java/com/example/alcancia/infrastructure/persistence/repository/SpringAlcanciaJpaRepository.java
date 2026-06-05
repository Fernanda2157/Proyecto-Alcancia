package com.universidad.alcancia.infrastructure.persistence.repository;

import com.universidad.alcancia.infrastructure.persistence.entity.AlcanciaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringAlcanciaJpaRepository extends JpaRepository<AlcanciaJpaEntity, Long> {}
