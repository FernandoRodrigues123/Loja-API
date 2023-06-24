package com.gestaoDeLoja.dto.gestor;

import com.gestaoDeLoja.domain.Gestor;

public record GestorVisualisadorDTO(Long id, String nome) {

    public GestorVisualisadorDTO(Gestor gestor){
        this(gestor.getId(), gestor.getNome());
    }

}
