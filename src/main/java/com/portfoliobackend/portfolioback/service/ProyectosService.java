package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.Proyectos;
import com.portfoliobackend.portfolioback.repository.ProyectosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProyectosService {
    private final ProyectosRepository proyectosRepository;
    
    @Autowired
    public ProyectosService(ProyectosRepository proyectosRepository) {
        this.proyectosRepository = proyectosRepository;
    }
    
    public List<Proyectos> getAllProyec() {
        return proyectosRepository.findAll();
    }

    public Optional<Proyectos> getByIdProyec(Long id) {
        return proyectosRepository.findById(id);
    }

    public Optional<Proyectos> getByNombreProyec(String titulo) {
        return proyectosRepository.findByTitulo(titulo);
    }

    public void saveProyec(Proyectos proyectos) {
        proyectosRepository.save(proyectos);
    }

    public void deleteProyec(Long id) {
        proyectosRepository.deleteById(id);
    }

    public boolean existsByIdProyec(Long id) {
        return proyectosRepository.existsById(id);
    }

    public boolean existsByNombreProyec(String titulo) {
        return proyectosRepository.existsByTitulo(titulo);
    }
}
