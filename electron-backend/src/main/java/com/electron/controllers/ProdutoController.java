package com.electron.controllers;

import com.electron.domain.Produto;
import com.electron.domain.dtos.ProdutoDTO;
import com.electron.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    // Criar novo produto
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody ProdutoDTO produtoDTO) {
        Produto novoProduto = produtoService.salvar(produtoDTO.toProduto());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    // Atualizar produto existente
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.atualizar(id, produtoAtualizado);
        return ResponseEntity.ok(produto);
    }

    // Excluir produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
