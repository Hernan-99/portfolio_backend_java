package com.portfoliobackend.portfolioback.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HabilidadesDurasDto {
    @NotBlank
    private String nombreTecnologia;
    
    @NotBlank
    private String iconoTecnologia;
    
    @NotBlank
    @Min(0)
    @Max(100)
    private int porcentaje;
    
    @NotBlank
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #e34c26")
    private String colorIcono;
    
    @NotBlank
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #e34c26")
    private String colorBarra;

    public HabilidadesDurasDto() {
    }

    public HabilidadesDurasDto(@NotBlank String nombreTecnologia, @NotBlank String iconoTecnologia, @NotBlank int porcentaje, @NotBlank String colorIcono, @NotBlank String colorBarra) {
        this.nombreTecnologia = nombreTecnologia;
        this.iconoTecnologia = iconoTecnologia;
        this.porcentaje = porcentaje;
        this.colorIcono = colorIcono;
        this.colorBarra = colorBarra;
    }
}
