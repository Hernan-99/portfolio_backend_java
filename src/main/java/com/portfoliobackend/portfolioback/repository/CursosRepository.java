package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.Cursos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long>{
    Optional<Cursos> findByNombreCurso(String nombre);
    boolean existsByNombreCurso(String nombre);
}
