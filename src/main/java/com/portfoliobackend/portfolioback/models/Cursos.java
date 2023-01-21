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
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nombreCurso;
    @NotNull
    @Size(min=5, max = 1000, message = "La descripcion debe ser menor a 1000 caracteres")
    private String descripcionCurso;
    @NotNull
    private String link;    
    @NotNull
    private String imgCurso;

    public Cursos() {
    }

    public Cursos(String nombreCurso, String descripcionCurso, String link, String imgCurso) {
        this.nombreCurso = nombreCurso;
        this.descripcionCurso = descripcionCurso;
        this.link = link;
        this.imgCurso = imgCurso;
    }
}
