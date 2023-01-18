package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.HabilidadesDuras;
import com.portfoliobackend.portfolioback.repository.HabilidadesDurasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HabilidadesDurasService {
    private final HabilidadesDurasRepository habilidadesDurasRepository;
    
    @Autowired
    public HabilidadesDurasService(HabilidadesDurasRepository habilidadesDurasRepository) {
        this.habilidadesDurasRepository = habilidadesDurasRepository;
    }
    
    public List<HabilidadesDuras> getAllHD() {
        return habilidadesDurasRepository.findAll();
    }

    public Optional<HabilidadesDuras> getOneHD(Long id) {
        return habilidadesDurasRepository.findById(id);
    }

    public Optional<HabilidadesDuras> getByNombreHD(String tecnologia) {
        return habilidadesDurasRepository.findByNombreTecnologia(tecnologia);
    }

    public void save(HabilidadesDuras habilidad) {
        habilidadesDurasRepository.save(habilidad);
    }

    public void deleteHD(Long id) {
        habilidadesDurasRepository.deleteById(id);
    }

    public boolean existsByIdHD(Long id) {
        return habilidadesDurasRepository.existsById(id);
    }

    public boolean existsByNombreHD(String tecnologia) {
        return habilidadesDurasRepository.existsByNombreTecnologia(tecnologia);
    }
}
