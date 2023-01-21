package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.Personas;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Long>{
    Optional<Personas> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
