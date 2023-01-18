package com.portfoliobackend.portfolioback.security.repository;

import com.portfoliobackend.portfolioback.security.enums.RolNombre;
import com.portfoliobackend.portfolioback.security.model.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
