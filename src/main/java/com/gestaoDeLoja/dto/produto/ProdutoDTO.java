package com.gestaoDeLoja.dto.produto;

import com.gestaoDeLoja.domain.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(

        @NotBlank
        String nome,
        @NotBlank
        String descricao,
        @NotNull
        Double preco,
        @NotNull
        Integer quantidadeEmEstoque
) {
        public ProdutoDTO(Produto produto){
                this(produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEmEstoque());
        }
}
