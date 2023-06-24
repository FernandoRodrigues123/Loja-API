package com.gestaoDeLoja.dto.altentication;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
