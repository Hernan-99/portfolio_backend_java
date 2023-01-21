package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.Experiencias;
import com.portfoliobackend.portfolioback.repository.ExperienciasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperienciasService {

    private final ExperienciasRepository experienciasRepository;
    
    @Autowired
    public ExperienciasService(ExperienciasRepository experienciasRepository) {
        this.experienciasRepository = experienciasRepository;
    }
    
    public List<Experiencias> getAllExp() {
        return experienciasRepository.findAll();
    }

    public Optional<Experiencias> getOneExp(Long id) {
        return experienciasRepository.findById(id);
    }

    public Optional<Experiencias> getByNombreExp(String puesto) {
        return experienciasRepository.findByPuesto(puesto);
    }

    public void save(Experiencias experiencia) {
        experienciasRepository.save(experiencia);
    }

    public void deleteExp(Long id) {
        experienciasRepository.deleteById(id);
    }

    public boolean existsByIdExp(Long id) {
        return experienciasRepository.existsById(id);
    }

    public boolean existsByNombreExp(String puesto) {
        return experienciasRepository.existsByPuesto(puesto);
    }
}
