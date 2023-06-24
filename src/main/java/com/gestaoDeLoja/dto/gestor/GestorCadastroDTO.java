package com.gestaoDeLoja.dto.gestor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record GestorCadastroDTO(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha

) {
}
