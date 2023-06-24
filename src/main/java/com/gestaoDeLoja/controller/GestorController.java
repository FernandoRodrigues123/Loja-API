package com.gestaoDeLoja.controller;

import com.gestaoDeLoja.domain.Gestor;
import com.gestaoDeLoja.dto.gestor.GestorAtualizarDTO;
import com.gestaoDeLoja.dto.gestor.GestorCadastroDTO;
import com.gestaoDeLoja.dto.gestor.GestorVisualisadorDTO;
import com.gestaoDeLoja.repository.GestorRepository;
import com.gestaoDeLoja.repository.GestorUsuarioRepository;
import com.gestaoDeLoja.usuarios.GestorUsuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private GestorRepository repository;
    @Autowired
    private GestorUsuarioRepository usuarioRepository;


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid GestorCadastroDTO gestorDTO, UriComponentsBuilder uriBuilder) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String senhaCrypt = passwordEncoder.encode(gestorDTO.senha()); //criptografando senha

        Gestor gestor = new Gestor();
        gestor.setNome(gestorDTO.nome());
        gestor.setSenha(senhaCrypt);
        gestor.setEmail(gestorDTO.email());//criando obj gestor

        URI uri = uriBuilder.path("/gestor/{id}").buildAndExpand(gestor.getId()).toUri();//url de retorno


        usuarioRepository.save( new GestorUsuario(gestor.getEmail(), gestor.getSenha()));
        repository.save(gestor);

        return ResponseEntity.created(uri).body(new GestorVisualisadorDTO(gestor));

    }


    @GetMapping("/{id}")
    public ResponseEntity buscaPorId(@PathVariable Long id) {
        Gestor gestor = repository.getReferenceById(id);
        return ResponseEntity.ok(new GestorVisualisadorDTO(gestor));

    }

    @GetMapping
    public ResponseEntity<Page<GestorVisualisadorDTO>> lista(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(GestorVisualisadorDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid GestorAtualizarDTO dados){
        Gestor gestor = repository.getReferenceById(dados.id());
        gestor.atualizar(dados);

        return ResponseEntity.ok(new GestorVisualisadorDTO(gestor));//devolve um http status 200 junto com o dto

    }
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
