package com.gestaoDeLoja.dto.produto;

import com.gestaoDeLoja.domain.Produto;
import jakarta.validation.constraints.NotNull;

public record ProdutoAtualizacaoDTO(

        @NotNull
        Long id,

        String nome,

        String descricao,

        Double preco,

        Integer quantidadeEmEstoque
) {
        public ProdutoAtualizacaoDTO(Produto produto){
                this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getQuantidadeEmEstoque());
        }
}
