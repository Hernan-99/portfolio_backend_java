package com.portfoliobackend.portfolioback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 80)
    private String nombre;
    @NotNull
    @Size(min = 2, max = 180)
    private String apellido;
    @NotNull
    @Size(min = 2, max = 180)
    private String stack;
    @NotNull
    @Size(min = 10, max = 5000)
    private String descripcion;
    
    private String imgPerfil;
    private String banner;
    private String cv;

    public Personas() {
    }

    public Personas(String nombre, String apellido, String stack, String descripcion, String imgPerfil, String banner, String cv) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.descripcion = descripcion;
        this.imgPerfil = imgPerfil;
        this.banner = banner;
        this.cv = cv;
    }
}
