package com.gestaoDeLoja.dto.gestor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GestorAtualizarDTO(
        @NotNull
        Long id,

        String nome,
        String senha

) {
}
