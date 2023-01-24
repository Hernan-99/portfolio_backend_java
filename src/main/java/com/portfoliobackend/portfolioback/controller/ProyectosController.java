package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.dto.ProyectosDto;
import com.portfoliobackend.portfolioback.models.Proyectos;
import com.portfoliobackend.portfolioback.service.ProyectosService;
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
@RequestMapping("/proyecto")
@CrossOrigin(origins = "https://hernan-sanchez-portfolio.web.app")
public class ProyectosController {
    private final ProyectosService proyectosService;

    public ProyectosController(ProyectosService proyectosService) {
        this.proyectosService = proyectosService;
    }
    
    @GetMapping("/allproyectos")
    public ResponseEntity<Proyectos> getAllProyec(){
        List<Proyectos> listaProyectos = proyectosService.getAllProyec();
        return new ResponseEntity(listaProyectos, HttpStatus.OK);
    } 
    
    @GetMapping("/detailpro/{id}")
    public ResponseEntity<Proyectos> getByIdCur(@PathVariable("id") Long id) {
        if (!proyectosService.existsByIdProyec(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyecto = proyectosService.getByIdProyec(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @GetMapping("/detailnombrepro/{nombre}")
    public ResponseEntity<Proyectos> getByNombreProyec(@PathVariable("nombre") String nombre) {
        if (!proyectosService.existsByNombreProyec(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyecto = proyectosService.getByNombreProyec(nombre).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PostMapping("/createproyec")
    public ResponseEntity<?> createCur(@RequestBody ProyectosDto proyectosDto) {
        if (StringUtils.isBlank(proyectosDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectosDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectosDto.getLink())) {
            return new ResponseEntity(new Mensaje("Ingresa el link del proyecto"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = new Proyectos(proyectosDto.getTitulo(),
                proyectosDto.getLink(),
                proyectosDto.getDescripcion());
        proyectosService.saveProyec(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto creado correctamente"), HttpStatus.CREATED);
    }
    
    @PutMapping("/updateproyec/{id}")
    public ResponseEntity<?> updateCur(@PathVariable("id") Long id, @RequestBody ProyectosDto proyectosDto) {
        if (!proyectosService.existsByIdProyec(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), HttpStatus.NOT_FOUND);
        }
        if (proyectosService.existsByNombreProyec(proyectosDto.getTitulo()) && proyectosService.getByNombreProyec(proyectosDto.getTitulo()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectosDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El titulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectosDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(proyectosDto.getLink())) {
            return new ResponseEntity(new Mensaje("El link es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = proyectosService.getByIdProyec(id).get();
        proyecto.setTitulo(proyectosDto.getTitulo());
        proyecto.setLink(proyectosDto.getLink());
        proyecto.setDescripcion(proyectosDto.getDescripcion());
        
        proyectosService.saveProyec(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/deleteproyec/{id}")
    public ResponseEntity<?> deleteCur(@PathVariable("id") Long id) {
        if (!proyectosService.existsByIdProyec(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), HttpStatus.NOT_FOUND);
        }
        proyectosService.deleteProyec(id);
        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
}
