package com.electron.controllers;

import com.electron.domain.SubgrupoProdServ;
import com.electron.services.SubgrupoProdServService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subgrupos-prod-serv")
public class SubgrupoProdServController {
    private final SubgrupoProdServService subgrupoProdServService;

    public SubgrupoProdServController(SubgrupoProdServService subgrupoProdServService) {
        this.subgrupoProdServService = subgrupoProdServService;
    }

    // Listar todos os subgrupos de produto/serviço
    @GetMapping
    public ResponseEntity<List<SubgrupoProdServ>> listarTodos() {
        List<SubgrupoProdServ> subgruposProdServ = subgrupoProdServService.listarTodos();
        return ResponseEntity.ok(subgruposProdServ);
    }

    // Buscar subgrupo de produto/serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<SubgrupoProdServ> buscarPorId(@PathVariable Long id) {
        SubgrupoProdServ subgrupoProdServ = subgrupoProdServService.buscarPorId(id);
        return ResponseEntity.ok(subgrupoProdServ);
    }

    // Criar novo subgrupo de produto/serviço
    @PostMapping
    public ResponseEntity<SubgrupoProdServ> criar(@RequestBody SubgrupoProdServ subgrupoProdServ) {
        SubgrupoProdServ novoSubgrupoProdServ = subgrupoProdServService.salvar(subgrupoProdServ);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSubgrupoProdServ);
    }

    // Atualizar subgrupo de produto/serviço existente
    @PutMapping("/{id}")
    public ResponseEntity<SubgrupoProdServ> atualizar(@PathVariable Long id, @RequestBody SubgrupoProdServ subgrupoProdServAtualizado) {
        SubgrupoProdServ subgrupoProdServ = subgrupoProdServService.atualizar(id, subgrupoProdServAtualizado);
        return ResponseEntity.ok(subgrupoProdServ);
    }

    // Excluir subgrupo de produto/serviço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        subgrupoProdServService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
