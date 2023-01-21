package com.portfoliobackend.portfolioback.controller;

import com.portfoliobackend.portfolioback.dto.HabilidadesBlandasDto;
import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.models.HabilidadesBlandas;
import com.portfoliobackend.portfolioback.service.HabilidadesBlandasService;
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
@RequestMapping("/habilidadB")
@CrossOrigin(origins = "http://localhost:4200")
public class HabilidadesBlandasController {

    private final HabilidadesBlandasService habilidadesBlandasService;

    public HabilidadesBlandasController(HabilidadesBlandasService habilidadesBlandasService) {
        this.habilidadesBlandasService = habilidadesBlandasService;
    }

    @GetMapping("/allhb")
    public ResponseEntity<List<HabilidadesBlandas>> listHB() {
        List<HabilidadesBlandas> listaHB = habilidadesBlandasService.getAllHB();
        return new ResponseEntity(listaHB, HttpStatus.OK);
    }

    @GetMapping("/detailhb/{id}")
    public ResponseEntity<HabilidadesBlandas> getByIdCur(@PathVariable("id") Long id) {
        if (!habilidadesBlandasService.existsByIdHB(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HabilidadesBlandas habilidadB = habilidadesBlandasService.getOneHB(id).get();
        return new ResponseEntity(habilidadB, HttpStatus.OK);
    }

    @GetMapping("/detailnombrehd/{nombre}")
    public ResponseEntity<HabilidadesBlandas> getByCursoNombreCur(@PathVariable("nombre") String nombre) {
        if (!habilidadesBlandasService.existsByNombreHB(nombre)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        HabilidadesBlandas habilidadesBlandas = habilidadesBlandasService.getByNombreHB(nombre).get();
        return new ResponseEntity(habilidadesBlandas, HttpStatus.OK);
    }

    @PostMapping("/createhb")
    public ResponseEntity<?> createCur(@RequestBody HabilidadesBlandasDto habilidadesBlandasDto) {
        if (StringUtils.isBlank(habilidadesBlandasDto.getNombreHabilidad())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getIconoHabilidad())) {
            return new ResponseEntity(new Mensaje("El icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getColorIcono())) {
            return new ResponseEntity(new Mensaje("El color del icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (habilidadesBlandasDto.getPorcentaje() < 0 || habilidadesBlandasDto.getPorcentaje() > 100) {
            return new ResponseEntity(new Mensaje("el porcentaje debe entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getColorBarra())) {
            return new ResponseEntity(new Mensaje("Ingresa un color para la barra de porcentaje"), HttpStatus.BAD_REQUEST);
        }

        HabilidadesBlandas habilidadesBlandas = new HabilidadesBlandas(
                habilidadesBlandasDto.getNombreHabilidad(),
                habilidadesBlandasDto.getIconoHabilidad(),
                habilidadesBlandasDto.getPorcentaje(),
                habilidadesBlandasDto.getColorIcono(),
                habilidadesBlandasDto.getColorBarra());
        habilidadesBlandasService.save(habilidadesBlandas);
        return new ResponseEntity(new Mensaje("Habilidad creada correctamente"), HttpStatus.CREATED);
    }

    @PutMapping("/updatehabilidadblanda/{id}")
    public ResponseEntity<?> updateHabilidadblanda(@PathVariable("id") Long id, @RequestBody HabilidadesBlandasDto habilidadesBlandasDto) {
        if (!habilidadesBlandasService.existsByIdHB(id)) {
            return new ResponseEntity(new Mensaje("La habilidad no existe"), HttpStatus.NOT_FOUND);
        }
        if (habilidadesBlandasService.existsByNombreHB(habilidadesBlandasDto.getNombreHabilidad()) && habilidadesBlandasService.getByNombreHB(habilidadesBlandasDto.getNombreHabilidad()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getNombreHabilidad())) {
            return new ResponseEntity(new Mensaje("El nombre de la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getIconoHabilidad())) {
            return new ResponseEntity(new Mensaje("El icono de la tecnologia es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getColorIcono())) {
            return new ResponseEntity(new Mensaje("El color del icono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (habilidadesBlandasDto.getPorcentaje() < 0 || habilidadesBlandasDto.getPorcentaje() > 100) {
            return new ResponseEntity(new Mensaje("el porcentaje debe entre 0 y 100"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(habilidadesBlandasDto.getColorBarra())) {
            return new ResponseEntity(new Mensaje("El color de la barra de porcentaje es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HabilidadesBlandas habilidadB = habilidadesBlandasService.getOneHB(id).get();
        habilidadB.setNombreHabilidad(habilidadesBlandasDto.getNombreHabilidad());
        habilidadB.setIconoHabilidad(habilidadesBlandasDto.getIconoHabilidad());
        habilidadB.setColorIcono(habilidadesBlandasDto.getColorIcono());
        habilidadB.setPorcentaje(habilidadesBlandasDto.getPorcentaje());
        habilidadB.setColorBarra(habilidadesBlandasDto.getColorBarra());
        habilidadesBlandasService.save(habilidadB);
        return new ResponseEntity(new Mensaje("Habilidad actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/deletehb/{id}")
    public ResponseEntity<?> deleteCur(@PathVariable("id") Long id) {
        if (!habilidadesBlandasService.existsByIdHB(id)) {
            return new ResponseEntity(new Mensaje("La habilidad no existe"), HttpStatus.NOT_FOUND);
        }
        habilidadesBlandasService.deleteHB(id);
        return new ResponseEntity(new Mensaje("Habilidad eliminada"), HttpStatus.OK);
    }

}
