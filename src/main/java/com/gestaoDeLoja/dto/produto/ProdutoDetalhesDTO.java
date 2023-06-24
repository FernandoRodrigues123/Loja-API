package com.gestaoDeLoja.dto.produto;

import com.gestaoDeLoja.domain.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDetalhesDTO(

        Long id,

        String nome,

        String descricao,

        Double preco,

        Integer quantidadeEmEstoque
) {
        public ProdutoDetalhesDTO(Produto produto){
                this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEmEstoque());
        }
}
