package com.gestaoDeLoja.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gestaoDeLoja.usuarios.GestorUsuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenServices {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(GestorUsuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create().withIssuer("api-loja.com").withSubject(usuario.getLogin()).withExpiresAt(dataExpiracao()).sign(algorithm);
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("api-loja.com")
                    .build()
                    .verify(tokenJWT).getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token invalido, ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of(("-03:00")));
    }
}
