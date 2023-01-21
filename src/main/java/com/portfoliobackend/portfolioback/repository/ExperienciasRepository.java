package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.Experiencias;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciasRepository extends JpaRepository<Experiencias, Long>{
    Optional<Experiencias> findByPuesto(String puesto);
    boolean existsByPuesto(String puesto);
}
