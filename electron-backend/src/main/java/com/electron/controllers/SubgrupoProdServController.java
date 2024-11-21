package com.electron.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.electron.domain.SubgrupoProdServ;
import com.electron.services.SubgrupoProdServService;

@RestController
@RequestMapping("/subgrupos-prod-serv")
public class SubgrupoProdServController {
    private final SubgrupoProdServService subgrupoProdServService;

    public SubgrupoProdServController(SubgrupoProdServService subgrupoProdServService) {
        this.subgrupoProdServService = subgrupoProdServService;
    }

    @GetMapping
    public ResponseEntity<List<SubgrupoProdServ>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(subgrupoProdServService.listarTodos(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubgrupoProdServ> buscarPorId(@PathVariable Long id) {
        SubgrupoProdServ subgrupoProdServ = subgrupoProdServService.buscarPorId(id);
        return ResponseEntity.ok(subgrupoProdServ);
    }

    @PostMapping
    public ResponseEntity<SubgrupoProdServ> criar(@RequestBody SubgrupoProdServ subgrupoProdServ) {
        SubgrupoProdServ novoSubgrupoProdServ = subgrupoProdServService.salvar(subgrupoProdServ);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSubgrupoProdServ);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubgrupoProdServ> atualizar(@PathVariable Long id, @RequestBody SubgrupoProdServ subgrupoProdServAtualizado) {
        SubgrupoProdServ subgrupoProdServ = subgrupoProdServService.atualizar(id, subgrupoProdServAtualizado);
        return ResponseEntity.ok(subgrupoProdServ);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        subgrupoProdServService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
