package com.electron.controllers;

import com.electron.domain.Vendedor;
import com.electron.services.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    // Listar todos os vendedores
    @GetMapping
    public ResponseEntity<List<Vendedor>> listarTodos() {
        List<Vendedor> vendedores = vendedorService.listarTodos();
        return ResponseEntity.ok(vendedores);
    }

    // Buscar vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarPorId(@PathVariable Long id) {
        Optional<Vendedor> vendedor = vendedorService.buscarPorId(id);
        return vendedor.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar um novo vendedor
    @PostMapping
    public ResponseEntity<Vendedor> criar(@RequestBody Vendedor vendedor) {
        Vendedor novoVendedor = vendedorService.salvar(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    // Atualizar um vendedor existente
    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> atualizar(@PathVariable Long id, @RequestBody Vendedor vendedorAtualizado) {
        try {
            Vendedor vendedorAtualizadoResult = vendedorService.atualizar(id, vendedorAtualizado);
            return ResponseEntity.ok(vendedorAtualizadoResult);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir um vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            vendedorService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
