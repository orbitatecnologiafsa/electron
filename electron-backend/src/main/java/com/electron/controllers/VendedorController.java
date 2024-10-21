package com.electron.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electron.domain.Vendedor;
import com.electron.services.VendedorService;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @GetMapping
    public ResponseEntity<List<Vendedor>> listarTodos() {
        return ResponseEntity.ok(vendedorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Vendedor vendedor) {
        vendedorService.criar(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> atualizar(@PathVariable Long id, @RequestBody Vendedor vendedor) {
        return ResponseEntity.ok(vendedorService.atualizar(id, vendedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vendedorService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}