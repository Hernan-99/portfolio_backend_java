package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.Cursos;
import com.portfoliobackend.portfolioback.repository.CursosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CursosService {

    private final CursosRepository cursosRepository;

    @Autowired
    public CursosService(CursosRepository cursosRepository) {
        this.cursosRepository = cursosRepository;
    }
    
    public List<Cursos> getAll() {
        return cursosRepository.findAll();
    }

    public Optional<Cursos> getById(Long id) {
        return cursosRepository.findById(id);
    }

    //public Optional<Cursos> getByCurso(String curso) {
    //    return cursosRepository.findByCurso(curso);
    //}
    public Optional<Cursos> getByCurso(String curso){
        return cursosRepository.findByNombreCurso(curso);
    }

    public void save(Cursos curso) {
        cursosRepository.save(curso);
    }

    public void delete(Long id) {
        cursosRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return cursosRepository.existsById(id);
    }

    public boolean existsByCurso(String curso) {
        return cursosRepository.existsByNombreCurso(curso);
    }

}
