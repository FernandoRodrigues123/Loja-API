package com.gestaoDeLoja.domain;

import com.gestaoDeLoja.dto.gestor.GestorAtualizarDTO;
import com.gestaoDeLoja.dto.gestor.GestorCadastroDTO;
import com.gestaoDeLoja.dto.gestor.GestorVisualisadorDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name="gestor")
@Entity(name = "Gestor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Gestor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String email;
    private String senha;


    public Gestor(GestorCadastroDTO gestorDTO) {
        this.email = gestorDTO.email();
        this.nome = gestorDTO.nome();
        this.senha = gestorDTO.senha();
    }

    public void atualizar(GestorAtualizarDTO dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
      if(dados.senha() != null){
          this.senha = dados.senha();
      }
    }
}
