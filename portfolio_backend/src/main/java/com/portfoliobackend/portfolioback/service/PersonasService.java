package com.portfoliobackend.portfolioback.service;

import com.portfoliobackend.portfolioback.models.Personas;
import com.portfoliobackend.portfolioback.repository.PersonasRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonasService {

    private final PersonasRepository personasRepository;

    @Autowired
    public PersonasService(PersonasRepository personasRepository) {
        this.personasRepository = personasRepository;
    }

    public List<Personas> list() {
        return personasRepository.findAll();
    }

    public Optional<Personas> getOne(Long id) {
        return personasRepository.findById(id);
    }

    public Optional<Personas> getByNombre(String nombre) {
        return personasRepository.findByNombre(nombre);
    }

    public void save(Personas personas) {
        personasRepository.save(personas);
    }

    public void delete(Long id) {
        personasRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return personasRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return personasRepository.existsByNombre(nombre);
    }

}
