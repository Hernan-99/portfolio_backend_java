package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.Proyectos;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectosRepository extends JpaRepository<Proyectos, Long>{
    Optional<Proyectos> findByTitulo(String titulo);
    boolean existsByTitulo(String titulo);
}
