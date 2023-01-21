package com.portfoliobackend.portfolioback.security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails{
    
    private String nombreUsuario;
    private String contrasenia;
    
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal() {
    }

    public UsuarioPrincipal(String nombreUsuario, String contrasenia, Collection<? extends GrantedAuthority> authorities) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal construir(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                        .collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombreUsuario(), 
                usuario.getContrasenia(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
