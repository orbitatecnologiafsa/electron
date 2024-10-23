package com.electron.controllers;

import com.electron.domain.Endereco;
import com.electron.services.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodos() {
        return ResponseEntity.ok(enderecoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.listarPorId(id));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Endereco>> listarPorPessoa(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(enderecoService.listarPorPessoa(pessoaId));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Endereco endereco) {
        enderecoService.criar(endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Endereco endereco) {
        enderecoService.atualizar(id, endereco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
