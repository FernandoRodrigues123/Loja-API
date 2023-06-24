package com.gestaoDeLoja.controller;

import com.gestaoDeLoja.domain.Gestor;
import com.gestaoDeLoja.domain.Produto;
import com.gestaoDeLoja.dto.gestor.GestorAtualizarDTO;
import com.gestaoDeLoja.dto.gestor.GestorVisualisadorDTO;
import com.gestaoDeLoja.dto.produto.ProdutoAtualizacaoDTO;
import com.gestaoDeLoja.dto.produto.ProdutoDTO;
import com.gestaoDeLoja.dto.produto.ProdutoDetalhesDTO;
import com.gestaoDeLoja.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid ProdutoDTO dados, UriComponentsBuilder uriBuilder) {
        Produto produto = repository.save(new Produto(dados));

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
        Produto produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDetalhesDTO>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ProdutoDetalhesDTO::new);
        return ResponseEntity.ok(page);
    }


    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid ProdutoAtualizacaoDTO dados){
        Produto produto = repository.getReferenceById(dados.id());
        produto.atualizar(dados);

        return ResponseEntity.ok(new ProdutoDTO(produto));//devolve um http status 200 junto com o dto

    }
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
