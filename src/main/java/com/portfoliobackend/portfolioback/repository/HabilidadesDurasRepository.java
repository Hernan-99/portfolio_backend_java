package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.HabilidadesDuras;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadesDurasRepository extends JpaRepository<HabilidadesDuras, Long>{
    Optional<HabilidadesDuras> findByNombreTecnologia(String tecnologia);
    boolean existsByNombreTecnologia(String tecnologia);
}
