package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.dto.PersonasDto;
import com.portfoliobackend.portfolioback.models.Personas;
import com.portfoliobackend.portfolioback.service.PersonasService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {

    private final PersonasService personasService;

    public PersonaController(PersonasService personasService) {
        this.personasService = personasService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Personas>> list() {
        List<Personas> list = personasService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Personas> getById(@PathVariable("id") Long id) {
        if (!personasService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Personas persona = personasService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Personas> getByNombre(@PathVariable("nombre") String nombre) {
        if (!personasService.existsByNombre(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Personas persona = personasService.getByNombre(nombre).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonasDto personasDto) {
        if (StringUtils.isBlank(personasDto.getNombre())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getApellido())) {
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getStack())) {
            return new ResponseEntity(new Mensaje("el stack es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Personas persona = new Personas(personasDto.getNombre(), personasDto.getApellido(),
                personasDto.getStack(), personasDto.getDescripcion(), personasDto.getImgPerfil(), personasDto.getBanner(),
                personasDto.getCv());
        personasService.save(persona);
        return new ResponseEntity(new Mensaje("persona creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PersonasDto personasDto) {
        if (!personasService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        if (personasService.existsByNombre(personasDto.getNombre()) && personasService.getByNombre(personasDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getNombre())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getApellido())) {
            return new ResponseEntity(new Mensaje("el apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getStack())) {
            return new ResponseEntity(new Mensaje("el stack es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personasDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("descripcion obligatoria"), HttpStatus.BAD_REQUEST);
        }


        Personas persona = personasService.getOne(id).get();
        persona.setNombre(personasDto.getNombre());
        persona.setApellido(personasDto.getApellido());
        persona.setStack(personasDto.getStack());
        persona.setDescripcion(personasDto.getDescripcion());
        persona.setImgPerfil(personasDto.getImgPerfil());
        persona.setBanner(personasDto.getBanner());
        persona.setCv(personasDto.getCv());
        
        personasService.save(persona);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!personasService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        personasService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

}
