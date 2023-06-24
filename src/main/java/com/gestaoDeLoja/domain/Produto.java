package com.gestaoDeLoja.domain;

import com.gestaoDeLoja.dto.produto.ProdutoAtualizacaoDTO;
import com.gestaoDeLoja.dto.produto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    @Column(name = "quantidade_em_estoque")
    private Integer quantidadeEmEstoque;


    public Produto(ProdutoDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
    }

    public void atualizar(ProdutoAtualizacaoDTO dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.descricao() != null){
            this.descricao = dados.descricao();
        }
        if(dados.preco() != null){
            this.preco = dados.preco();
        }
        if(dados.quantidadeEmEstoque() != null){
            this.quantidadeEmEstoque = dados.quantidadeEmEstoque();
        }

    }
}
