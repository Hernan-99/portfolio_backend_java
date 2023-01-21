package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.CursosDto;
import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.models.Cursos;
import com.portfoliobackend.portfolioback.service.CursosService;
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
@RequestMapping("/curso")
@CrossOrigin(origins = "http://localhost:4200")
public class CursosController {

    private final CursosService cursosService;

    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    @GetMapping("/allcur")
    public ResponseEntity<List<Cursos>> listCur() {
        List<Cursos> listaCursos = cursosService.getAll();
        return new ResponseEntity(listaCursos, HttpStatus.OK);
    }

    @GetMapping("/detailcur/{id}")
    public ResponseEntity<Cursos> getByIdCur(@PathVariable("id") Long id) {
        if (!cursosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Cursos curso = cursosService.getById(id).get();
        return new ResponseEntity(curso, HttpStatus.OK);
    }

    @GetMapping("/detailnombre/{nombre}")
    public ResponseEntity<Cursos> getByCursoNombreCur(@PathVariable("nombre") String nombre) {
        if (!cursosService.existsByCurso(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Cursos curso = cursosService.getByCurso(nombre).get();
        return new ResponseEntity(curso, HttpStatus.OK);
    }

    @PostMapping("/createcur")
    public ResponseEntity<?> createCur(@RequestBody CursosDto cursosDto) {
        if (StringUtils.isBlank(cursosDto.getNombreCurso())) {
            return new ResponseEntity(new Mensaje("El nombre del curso es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getDescripcionCurso())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getLink())) {
            return new ResponseEntity(new Mensaje("Ingresa el link de la institucion"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getImgCurso())) {
            return new ResponseEntity(new Mensaje("Ingresa una imagen ilustrativa"), HttpStatus.BAD_REQUEST);
        }

        Cursos curso = new Cursos(
                cursosDto.getNombreCurso(), 
                cursosDto.getDescripcionCurso(),
                cursosDto.getLink(),
                cursosDto.getImgCurso());
        cursosService.save(curso);
        return new ResponseEntity(new Mensaje("Curso creado correctamente"), HttpStatus.CREATED);
    }

    @PutMapping("/updatecur/{id}")
    public ResponseEntity<?> updateCur(@PathVariable("id") Long id, @RequestBody CursosDto cursosDto) {
        if (!cursosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El curso no existe"), HttpStatus.NOT_FOUND);
        }
        if (cursosService.existsByCurso(cursosDto.getNombreCurso()) && cursosService.getByCurso(cursosDto.getNombreCurso()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese curso ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getNombreCurso())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getDescripcionCurso())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getLink())) {
            return new ResponseEntity(new Mensaje("El link es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(cursosDto.getImgCurso())) {
            return new ResponseEntity(new Mensaje("La url de la imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Cursos curso = cursosService.getById(id).get();
        curso.setNombreCurso(cursosDto.getNombreCurso());
        curso.setDescripcionCurso(cursosDto.getDescripcionCurso());
        curso.setLink(cursosDto.getLink());
        curso.setImgCurso(cursosDto.getImgCurso());
        cursosService.save(curso);
        return new ResponseEntity(new Mensaje("Curso actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/deletecur/{id}")
    public ResponseEntity<?> deleteCur(@PathVariable("id") Long id) {
        if (!cursosService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El curso no existe"), HttpStatus.NOT_FOUND);
        }
        cursosService.delete(id);
        return new ResponseEntity(new Mensaje("Curso eliminado"), HttpStatus.OK);
    }
}
