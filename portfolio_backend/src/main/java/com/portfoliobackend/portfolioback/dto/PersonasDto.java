package com.portfoliobackend.portfolioback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonasDto {

    @NotBlank
    private String nombre;
    @NotBlank
    
    private String apellido;
    @NotBlank
    
    private String stack;
    @NotBlank
    
    private String descripcion;

    private String imgPerfil;
    private String banner;
    private String cv;

    public PersonasDto() {
    }

    public PersonasDto(@NotBlank String nombre, @NotBlank String apellido, @NotBlank String stack, @NotBlank String descripcion, String imgPerfil, String banner, String cv) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.stack = stack;
        this.descripcion = descripcion;
        this.imgPerfil = imgPerfil;
        this.banner = banner;
        this.cv = cv;
    }
    

}
