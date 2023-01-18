package com.portfoliobackend.portfolioback.security.jwt;

import com.portfoliobackend.portfolioback.security.service.UserDetailsServiceImpl;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter{
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest req, 
            HttpServletResponse res, 
            FilterChain filterChain) throws ServletException, IOException {
        
        try {
           String token = obtenerToken(req);
           
           if(token != null && jwtProvider.validarToken(token)){
               String nombreUsuario = jwtProvider.obtenerNombreUsuarioToken(token);
               UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
               
               UsernamePasswordAuthenticationToken auth =
                       new UsernamePasswordAuthenticationToken(userDetails,
                       null, userDetails.getAuthorities());
               SecurityContextHolder.getContext().setAuthentication(auth);
           }
        } 
        catch (Exception e) {
            logger.error("Error en el metodo doFilter" + e.getMessage());
        }
        filterChain.doFilter(req, res);
    }
    
    private String obtenerToken(HttpServletRequest req){
        String header = req.getHeader("Authorization");
        
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
