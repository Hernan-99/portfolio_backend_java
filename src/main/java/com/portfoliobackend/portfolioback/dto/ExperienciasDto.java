package com.portfoliobackend.portfolioback.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExperienciasDto {
    
    @NotBlank
    private String puesto;

    @NotBlank
    private String imgTrabajo;

     @NotBlank
     private String descripcionPuesto;    

    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @NotBlank
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    public ExperienciasDto() {
    }

    public ExperienciasDto(@NotBlank String puesto, 
            @NotBlank String imgTrabajo, 
            @NotBlank String descripcionPuesto,
            @NotBlank Date fechaInicio,
            @NotBlank Date fechaFin
    ) {
        this.puesto = puesto;
        this.imgTrabajo = imgTrabajo;
        this.descripcionPuesto = descripcionPuesto;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

}
