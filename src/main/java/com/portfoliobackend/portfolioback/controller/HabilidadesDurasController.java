package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.HabilidadesDurasDto;
import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.models.HabilidadesDuras;
import com.portfoliobackend.portfolioback.service.HabilidadesDurasService;
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
@RequestMapping("/habilidadD")
@CrossOrigin(origins = "https://hernan-sanchez-portfolio.web.app")
public class HabilidadesDurasController {
    private final HabilidadesDurasService habilidadesDurasService;
    
    public HabilidadesDurasController(com.portfoliobackend.portfolioback.service.HabilidadesDurasService habilidadesDurasService) {
        this.habilidadesDurasService = habilidadesDurasService;
    }
    
    @GetMapping("/allhd")
    public ResponseEntity<List<HabilidadesDuras>> listHD() {
        List<HabilidadesDuras> listaHD = habilidadesDurasService.getAllHD();
        return new ResponseEntity(listaHD, HttpStatus.OK);
    }
    
    @GetMapping("/detailhd/{id}")
    public ResponseEntity<HabilidadesDuras> getByIdCur(@PathVariable("id") Long id) {
        if (!habilidadesDurasService.existsByIdHD(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HabilidadesDuras habilidadD = habilidadesDurasService.getOneHD(id).get();
        return new ResponseEntity(habilidadD, HttpStatus.OK);
    }

    @GetMapping("/detailnombrehd/{nombre}")
    public ResponseEntity<HabilidadesDuras> getByCursoNombreCur(@PathVariable("nombre") String nombre) {
        if (!habilidadesDurasService.existsByNombreHD(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HabilidadesDuras habilidadesDuras = habilidadesDurasService.getByNombreHD(nombre).get();
        return new ResponseEntity(habilidadesDuras, HttpStatus.OK);
    }
    
    @PostMapping("/createhd")
    public ResponseEntity<?> createCur(@RequestBody HabilidadesDurasDto habilidadesDurasDto) {
        if (StringUtils.isBlank(habilidadesDurasDto.getNombreTecnologia())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getIconoTecnologia())) {
            return new ResponseEntity(new Mensaje("El icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getColorIcono())) {
            return new ResponseEntity(new Mensaje("El color del icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(habilidadesDurasDto.getPorcentaje() < 0 || habilidadesDurasDto.getPorcentaje() > 100 ){
            return new ResponseEntity(new Mensaje("el porcentaje debe entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getColorBarra())) {
            return new ResponseEntity(new Mensaje("Ingresa un color para la barra de porcentaje"), HttpStatus.BAD_REQUEST);
        }

        HabilidadesDuras habilidadesDuras = new HabilidadesDuras(
                habilidadesDurasDto.getNombreTecnologia(),
                habilidadesDurasDto.getIconoTecnologia(),
                habilidadesDurasDto.getPorcentaje(),
                habilidadesDurasDto.getColorIcono(),
                habilidadesDurasDto.getColorBarra());
        habilidadesDurasService.save(habilidadesDuras);
        return new ResponseEntity(new Mensaje("Habilidad creada correctamente"), HttpStatus.CREATED);
    }
    
    @PutMapping("/updatehd/{id}")
    public ResponseEntity<?> updateHabilidadDura(@PathVariable("id") Long id, @RequestBody HabilidadesDurasDto habilidadesDurasDto) {
        if (!habilidadesDurasService.existsByIdHD(id)) {
            return new ResponseEntity(new Mensaje("El proyecto no existe"), HttpStatus.NOT_FOUND);
        }
        if (habilidadesDurasService.existsByNombreHD(habilidadesDurasDto.getNombreTecnologia()) && habilidadesDurasService.getByNombreHD(habilidadesDurasDto.getNombreTecnologia()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getNombreTecnologia())) {
            return new ResponseEntity(new Mensaje("El nombre de la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getIconoTecnologia())) {
            return new ResponseEntity(new Mensaje("El icono de la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getColorIcono())) {
            return new ResponseEntity(new Mensaje("El color del icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(habilidadesDurasDto.getPorcentaje() < 0 || habilidadesDurasDto.getPorcentaje() > 100 ){
            return new ResponseEntity(new Mensaje("el porcentaje debe entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesDurasDto.getColorBarra())) {
            return new ResponseEntity(new Mensaje("El color de la barra de porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HabilidadesDuras habilidadD = habilidadesDurasService.getOneHD(id).get();
        habilidadD.setNombreTecnologia(habilidadesDurasDto.getNombreTecnologia());
        habilidadD.setIconoTecnologia(habilidadesDurasDto.getIconoTecnologia());
        habilidadD.setColorIcono(habilidadesDurasDto.getColorIcono());
        habilidadD.setPorcentaje(habilidadesDurasDto.getPorcentaje());
        habilidadD.setColorBarra(habilidadesDurasDto.getColorBarra());
        habilidadesDurasService.save(habilidadD);
        return new ResponseEntity(new Mensaje("Habilidad actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/deletehd/{id}")
    public ResponseEntity<?> deleteCur(@PathVariable("id") Long id) {
        if (!habilidadesDurasService.existsByIdHD(id)) {
            return new ResponseEntity(new Mensaje("La habilidad no existe"), HttpStatus.NOT_FOUND);
        }
        habilidadesDurasService.deleteHD(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }
}
