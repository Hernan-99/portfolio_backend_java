package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.EducacionesDto;
import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.models.Educaciones;
import com.portfoliobackend.portfolioback.service.EducacionesService;
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
@RequestMapping("/educacion")
@CrossOrigin(origins = "https://hernan-sanchez-portfolio.web.app")
public class EducacionesController {

    private final EducacionesService educacionesService;

    public EducacionesController(EducacionesService educacionesService) {
        this.educacionesService = educacionesService;
    }

    @GetMapping("/alledu")
    public ResponseEntity<List<Educaciones>> listEdu() {
        List<Educaciones> listaExp = educacionesService.getAllEdu();
        return new ResponseEntity<>(listaExp, HttpStatus.OK);
    }

    @GetMapping("/detailedu/{id}")
    public ResponseEntity<Educaciones> getByIdEdu(@PathVariable("id") Long id) {
        if (!educacionesService.existsByIdEdu(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educaciones educacion = educacionesService.getOneEdu(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @GetMapping("/detailnombreedu/{nombre}")
    public ResponseEntity<Educaciones> getByNombreEdu(@PathVariable("nombre") String nombre) {
        if (!educacionesService.existsByNombreEdu(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educaciones educacion = educacionesService.getByNombreEdu(nombre).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }

    @PostMapping("/createedu")
    public ResponseEntity<?> createEdu(@RequestBody EducacionesDto educacionesDto) {
        if (StringUtils.isBlank(educacionesDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("El nombre de la institucion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educacionesDto.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fecha de inicio es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (educacionesDto.getFechaFin() == null) {
            return new ResponseEntity(new Mensaje("La fecha de din es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionesDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educaciones educaciones = new Educaciones(
                educacionesDto.getInstitucion(),
                educacionesDto.getTitulo(),
                educacionesDto.getImgEdu(),
                educacionesDto.getFechaInicio(),
                educacionesDto.getFechaFin());
        educacionesService.save(educaciones);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);
    }

    @PutMapping("/updateedu/{id}")
    public ResponseEntity<?> updateEdu(@PathVariable("id") Long id, @RequestBody EducacionesDto educacionesDto) {
        if (!educacionesService.existsByIdEdu(id)) {
            return new ResponseEntity(new Mensaje("No existe"), HttpStatus.NOT_FOUND);
        }
        if (educacionesService.existsByNombreEdu(educacionesDto.getInstitucion()) && educacionesService.getByNombreEdu(educacionesDto.getInstitucion()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionesDto.getInstitucion())) {
            return new ResponseEntity(new Mensaje("el nombre de la institucion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educacionesDto.getFechaInicio() == null) {
            return new ResponseEntity(new Mensaje("La fecha de inicio es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (educacionesDto.getFechaFin() == null) {
            return new ResponseEntity(new Mensaje("La fecha de din es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educacionesDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Educaciones educacion = educacionesService.getOneEdu(id).get();
        educacion.setInstitucion(educacionesDto.getInstitucion());
        educacion.setTitulo(educacionesDto.getTitulo());
        educacion.setImgEdu(educacionesDto.getImgEdu());
        educacion.setFechaInicio(educacionesDto.getFechaInicio());
        educacion.setFechaFin(educacionesDto.getFechaFin());

        educacionesService.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/deleteedu/{id}")
    public ResponseEntity<?> deleteEdu(@PathVariable("id") Long id) {
        if (!educacionesService.existsByIdEdu(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        educacionesService.deleteEdu(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }
}
