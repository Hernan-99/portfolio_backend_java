package com.portfoliobackend.portfolioback.security.controller;

import com.portfoliobackend.portfolioback.dto.Mensaje;
import com.portfoliobackend.portfolioback.security.dto.JwtDto;
import com.portfoliobackend.portfolioback.security.dto.LoginUsuario;
import com.portfoliobackend.portfolioback.security.dto.NuevoUsuario;
import com.portfoliobackend.portfolioback.security.enums.RolNombre;
import com.portfoliobackend.portfolioback.security.jwt.JwtProvider;
import com.portfoliobackend.portfolioback.security.model.Rol;
import com.portfoliobackend.portfolioback.security.model.Usuario;
import com.portfoliobackend.portfolioback.security.service.RolService;
import com.portfoliobackend.portfolioback.security.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario,
            BindingResult bindingResult){
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        
        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity(new Mensaje("ese usuario ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Usuario usuario = 
                new Usuario(
                nuevoUsuario.getNombreUsuario(),
                passwordEncoder.encode(nuevoUsuario.getContrasenia()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.CREATED);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, 
            BindingResult bindingResult){
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        }
        
        Authentication authentication = 
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getContrasenia()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generarToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
