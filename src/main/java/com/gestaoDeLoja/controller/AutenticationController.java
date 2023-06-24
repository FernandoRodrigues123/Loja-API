package com.gestaoDeLoja.controller;

import com.gestaoDeLoja.dto.altentication.DadosTokenJWT;
import com.gestaoDeLoja.security.TokenServices;
import com.gestaoDeLoja.dto.altentication.DadosLogin;
import com.gestaoDeLoja.usuarios.GestorUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired

    private TokenServices tokenServices;


    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosLogin dados){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authenticate = manager.authenticate(authenticationToken);

        String tokenJWT = tokenServices.gerarToken((GestorUsuario) authenticate.getPrincipal());


        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));

    }

}
