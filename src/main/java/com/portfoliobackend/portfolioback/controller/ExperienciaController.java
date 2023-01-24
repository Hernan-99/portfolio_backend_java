package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.ExperienciasDto;
import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.models.Experiencias;
import com.portfoliobackend.portfolioback.service.ExperienciasService;
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
@RequestMapping("/experiencia")
@CrossOrigin(origins = "https://hernan-sanchez-portfolio.web.app")
public class ExperienciaController {

    private final ExperienciasService experienciasService;

    public ExperienciaController(ExperienciasService experienciasService) {
        this.experienciasService = experienciasService;
    }

    @GetMapping("/allexp")
    public ResponseEntity<List<Experiencias>> listExp() {
        List<Experiencias> listaExp = experienciasService.getAllExp();
        return new ResponseEntity(listaExp, HttpStatus.OK);
    }

    @GetMapping("/detailexp/{id}")
    public ResponseEntity<Experiencias> getByIdExp(@PathVariable("id") Long id) {
        if (!experienciasService.existsByIdExp(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencias experiencia = experienciasService.getOneExp(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @GetMapping("/detailnombreexp/{nombre}")
    public ResponseEntity<Experiencias> getByNombreExp(@PathVariable("nombre") String nombre) {
        if (!experienciasService.existsByNombreExp(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experiencias experiencia = experienciasService.getByNombreExp(nombre).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    
    @PostMapping("/createexp")
    public ResponseEntity<?> createExp(@RequestBody ExperienciasDto experienciasDto) {
        if (StringUtils.isBlank(experienciasDto.getPuesto())) {
            return new ResponseEntity(new Mensaje("El nombre del puesto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciasDto.getDescripcionPuesto())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (experienciasDto.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fecha de inicio es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (experienciasDto.getFechaFin() == null ) {
            return new ResponseEntity(new Mensaje("La fecha de fin es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Experiencias experiencia = new Experiencias(
                experienciasDto.getPuesto(), 
                experienciasDto.getDescripcionPuesto(),
                experienciasDto.getImgTrabajo(), 
                experienciasDto.getFechaInicio(), 
                experienciasDto.getFechaFin());
        experienciasService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia creada"), HttpStatus.OK);
    }

    @PutMapping("/updateexp/{id}")
    public ResponseEntity<?> updateExp(@PathVariable("id") Long id, @RequestBody ExperienciasDto experienciasDto) {
        if (!experienciasService.existsByIdExp(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (experienciasService.existsByNombreExp(experienciasDto.getPuesto()) && experienciasService.getByNombreExp(experienciasDto.getPuesto()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciasDto.getPuesto())) {
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciasDto.getDescripcionPuesto())) {
            return new ResponseEntity(new Mensaje("la descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienciasDto.getImgTrabajo())) {
            return new ResponseEntity(new Mensaje("Debes proporsinar una url de img"), HttpStatus.BAD_REQUEST);
        }
        if (experienciasDto.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fecha de inicio es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (experienciasDto.getFechaFin() == null) {
            return new ResponseEntity(new Mensaje("La fecha de fin es obligatoria"), HttpStatus.BAD_REQUEST);
        }


        Experiencias experiencia = experienciasService.getOneExp(id).get();
        experiencia.setPuesto(experienciasDto.getPuesto());
        experiencia.setDescripcionPuesto(experienciasDto.getDescripcionPuesto());
        experiencia.setImgTrabajo(experienciasDto.getImgTrabajo());
        experiencia.setFechaInicio(experienciasDto.getFechaInicio());
        experiencia.setFechaFin(experienciasDto.getFechaFin());
        
        experienciasService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteexp/{id}")
    public ResponseEntity<?> deleteExp(@PathVariable("id") Long id) {
        if (!experienciasService.existsByIdExp(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        experienciasService.deleteExp(id);
        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
}