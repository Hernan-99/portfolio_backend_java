package com.portfoliobackend.portfolioback.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Experiencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String puesto;
    @NotNull
    private String descripcionPuesto;

    @NotNull
    private String imgTrabajo;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    public Experiencias() {
    }

    public Experiencias(String puesto, 
            String descripcionPuesto, 
            String imgTrabajo, Date fechaFin, 
            Date fechaInicio) {
        this.puesto = puesto;
        this.descripcionPuesto = descripcionPuesto;
        this.imgTrabajo = imgTrabajo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

}
