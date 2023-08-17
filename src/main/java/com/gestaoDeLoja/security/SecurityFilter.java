package com.gestaoDeLoja.security;

import com.gestaoDeLoja.repository.GestorUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenServices tokenServices;

    @Autowired
    private GestorUsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWT = getToken(request);

        if (tokenJWT != null) {

            String subject = tokenServices.getSubject(tokenJWT);
            var gestorUsuario = repository.findByLogin(subject);

            var auticacao = new UsernamePasswordAuthenticationToken(gestorUsuario, null, gestorUsuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auticacao);
        }


        filterChain.doFilter(request, response);
    }

    //pega token pelo cabecario autorization da requisicao
    private String getToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
