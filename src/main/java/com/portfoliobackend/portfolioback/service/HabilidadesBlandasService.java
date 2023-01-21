package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.HabilidadesBlandas;
import com.portfoliobackend.portfolioback.repository.HabilidadesBlandasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HabilidadesBlandasService {
    private final HabilidadesBlandasRepository habilidadesBlandasRepository;

    @Autowired
    public HabilidadesBlandasService(HabilidadesBlandasRepository habilidadesBlandasRepository) {
        this.habilidadesBlandasRepository = habilidadesBlandasRepository;
    }
    
    public List<HabilidadesBlandas> getAllHB() {
        return habilidadesBlandasRepository.findAll();
    }

    public Optional<HabilidadesBlandas> getOneHB(Long id) {
        return habilidadesBlandasRepository.findById(id);
    }

    public Optional<HabilidadesBlandas> getByNombreHB(String habilidad) {
        return habilidadesBlandasRepository.findByNombreHabilidad(habilidad);
    }

    public void save(HabilidadesBlandas habilidad) {
        habilidadesBlandasRepository.save(habilidad);
    }

    public void deleteHB(Long id) {
        habilidadesBlandasRepository.deleteById(id);
    }

    public boolean existsByIdHB(Long id) {
        return habilidadesBlandasRepository.existsById(id);
    }

    public boolean existsByNombreHB(String habilidad) {
        return habilidadesBlandasRepository.existsByNombreHabilidad(habilidad);
    }
}
