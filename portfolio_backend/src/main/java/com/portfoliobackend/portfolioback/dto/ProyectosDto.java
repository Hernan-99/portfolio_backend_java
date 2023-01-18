package com.portfoliobackend.portfolioback.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProyectosDto {
    @NotBlank
    private String titulo;
    @NotBlank
    private String link;
    
    @NotBlank
    @Size(min=1, max=150, message = "Ingrese una breve descripcion")
    private String descripcion;
    

    public ProyectosDto() {
    }

    public ProyectosDto(@NotBlank String titulo, 
            @NotBlank String link, 
            @NotBlank @Size String descripcion) {
        this.titulo = titulo;
        this.link = link;
        this.descripcion = descripcion;
    }
    
    
}