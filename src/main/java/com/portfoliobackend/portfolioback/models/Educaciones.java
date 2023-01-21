package com.portfoliobackend.portfolioback.models;

import java.text.SimpleDateFormat;
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
@Getter @Setter
public class Educaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String institucion;
    @NotNull
    private String titulo;
    @NotNull
    private String imgEdu;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicio;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFin;

    public Educaciones() {
    }

    public Educaciones(String institucion, String titulo, String imgEdu, Date fechaInicio, Date fechaFin ) {
        this.institucion = institucion;
        this.titulo = titulo;
        this.imgEdu = imgEdu;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
