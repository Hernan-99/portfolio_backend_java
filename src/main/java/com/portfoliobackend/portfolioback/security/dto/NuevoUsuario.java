package com.portfoliobackend.portfolioback.security.dto;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoUsuario {

    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String contrasenia;
    private Set<String> roles = new HashSet<>();
}
