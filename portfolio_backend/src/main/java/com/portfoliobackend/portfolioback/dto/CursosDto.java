package com.portfoliobackend.portfolioback.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CursosDto {
    @NotBlank
    private String nombreCurso;
    @NotBlank
    private String descripcionCurso;
    @NotBlank
    private String link;
    @NotBlank
    private String imgCurso;

    public CursosDto() {
    }

    public CursosDto(@NotBlank String nombreCurso, @NotBlank String descripcionCurso, 
            @NotBlank String link, @NotBlank String imgCurso) {
        this.nombreCurso = nombreCurso;
        this.descripcionCurso = descripcionCurso;
        this.link = link;
        this.imgCurso = imgCurso;
    }

}
