package com.portfoliobackend.portfolioback.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class HabilidadesDuras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nombreTecnologia;
    
    @NotNull
    private String iconoTecnologia;
    
    @NotNull
    @Min(0)
    @Max(100)
    private int porcentaje;
    
    @NotNull
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #e34c26")
    private String colorIcono;
    
    @NotNull
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #e34c26")
    private String colorBarra;

    public HabilidadesDuras() {
    }

    public HabilidadesDuras(String nombreTecnologia, String iconoTecnologia, int porcentaje, String colorIcono, String colorBarra) {
        this.nombreTecnologia = nombreTecnologia;
        this.iconoTecnologia = iconoTecnologia;
        this.porcentaje = porcentaje;
        this.colorIcono = colorIcono;
        this.colorBarra = colorBarra;
    }
}
