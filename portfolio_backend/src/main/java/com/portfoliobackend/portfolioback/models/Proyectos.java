package com.portfoliobackend.portfolioback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Proyectos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titulo;
    @NotNull
    private String link;
    @NotNull
    private String descripcion;
  
    public Proyectos() {
    }

    public Proyectos(String titulo, 
            String link, 
            String descripcion) {
        this.titulo = titulo;
        this.link = link;
        this.descripcion = descripcion;
    }

}
