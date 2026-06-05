package com.example.alcancia.repository;

import com.example.alcancia.entity.AlcanciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// CAPA: Repository — Spring Data JPA genera el CRUD
@Repository
public interface AlcanciaRepository extends JpaRepository<AlcanciaEntity, Long> {
}
