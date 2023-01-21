package com.portfoliobackend.portfolioback.repository;

import com.portfoliobackend.portfolioback.models.Educaciones;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducacionesRepository extends JpaRepository<Educaciones, Long>{
    Optional<Educaciones> findByInstitucion(String institucion);
    boolean existsByInstitucion(String institucion);
}
