package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.Educaciones;
import com.portfoliobackend.portfolioback.repository.EducacionesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EducacionesService {
    private final EducacionesRepository educacionesRepository;
    
    @Autowired
    public EducacionesService(EducacionesRepository educacionesRepository) {
        this.educacionesRepository = educacionesRepository;
    }
    
    public List<Educaciones> getAllEdu() {
        return educacionesRepository.findAll();
    }

    public Optional<Educaciones> getOneEdu(Long id) {
        return educacionesRepository.findById(id);
    }

    public Optional<Educaciones> getByNombreEdu(String institucion) {
        return educacionesRepository.findByInstitucion(institucion);
    }

    public void save(Educaciones educacion) {
        educacionesRepository.save(educacion);
    }

    public void deleteEdu(Long id) {
        educacionesRepository.deleteById(id);
    }

    public boolean existsByIdEdu(Long id) {
        return educacionesRepository.existsById(id);
    }

    public boolean existsByNombreEdu(String institucion) {
        return educacionesRepository.existsByInstitucion(institucion);
    }
}
