package com.gestaoDeLoja.repository;

import com.gestaoDeLoja.domain.Gestor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GestorRepository extends JpaRepository<Gestor, Long> {
}
