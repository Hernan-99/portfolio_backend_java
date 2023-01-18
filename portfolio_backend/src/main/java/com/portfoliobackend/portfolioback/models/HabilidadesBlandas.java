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
@Getter
@Setter
public class HabilidadesBlandas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombreHabilidad;
    @NotNull
    private String iconoHabilidad;

    @NotNull
    private int porcentaje;

    @NotNull
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #afe0ce")
    private String colorIcono;

    @NotNull
    @Size(min=4, max=7, message="Ingrese un codigo de color hexadecimal. Ej: #afe0ce")
    private String colorBarra;

    public HabilidadesBlandas() {
    }

    public HabilidadesBlandas(String nombreHabilidad, String iconoHabilidad, int porcentaje, String colorIcono, String colorBarra) {
        this.nombreHabilidad = nombreHabilidad;
        this.iconoHabilidad = iconoHabilidad;
        this.porcentaje = porcentaje;
        this.colorIcono = colorIcono;
        this.colorBarra = colorBarra;
    }

}
