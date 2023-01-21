package com.portfoliobackend.portfolioback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfoliobackend.portfolioback.models.HabilidadesBlandas;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadesBlandasRepository extends JpaRepository<HabilidadesBlandas, Long>{
    Optional<HabilidadesBlandas> findByNombreHabilidad(String habilidad);
    boolean existsByNombreHabilidad(String habilidad);
}
