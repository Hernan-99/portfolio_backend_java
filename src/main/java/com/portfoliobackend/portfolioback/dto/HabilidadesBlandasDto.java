package com.portfoliobackend.portfolioback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HabilidadesBlandasDto {

    @NotBlank
    private String nombreHabilidad;
    @NotBlank
    private String iconoHabilidad;

    @NotBlank
    @Size(min = 0, max = 100)
    private int porcentaje;

    @NotBlank
    private String colorIcono;

    @NotBlank
    private String colorBarra;

    public HabilidadesBlandasDto() {
    }

    public HabilidadesBlandasDto(@NotBlank String nombreHabilidad, @NotBlank String iconoHabilidad,
            @NotBlank int porcentaje, @NotBlank String colorIcono, @NotBlank String colorBarra) {
        this.nombreHabilidad = nombreHabilidad;
        this.iconoHabilidad = iconoHabilidad;
        this.porcentaje = porcentaje;
        this.colorIcono = colorIcono;
        this.colorBarra = colorBarra;
    }

}
