package com.portfoliobackend.portfolioback.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducacionesDto {
    
    @NotBlank
    private String institucion;
    @NotBlank
    private String titulo;
    @NotBlank
    private String imgEdu;
    
    
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public EducacionesDto(@NotBlank String institucion, @NotBlank String titulo, 
            @NotBlank String imgEdu, @NotBlank Date fechaInicio, @NotBlank Date fechaFin) {
        this.institucion = institucion;
        this.titulo = titulo;
        this.imgEdu = imgEdu;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    
    
}
