package com.electron.controllers;

import com.electron.domain.Vendedor;
import com.electron.domain.dtos.VendedorDTO;
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

    @GetMapping
    public ResponseEntity<List<Vendedor>> listarTodos() {
        List<Vendedor> vendedores = vendedorService.listarTodos();
        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vendedor> buscarPorId(@PathVariable Long id) {
        Vendedor vendedor = vendedorService.buscarPorId(id);
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping
    public ResponseEntity<Vendedor> criar(@RequestBody VendedorDTO vendedorDTO) {
        Vendedor novoVendedor = vendedorService.salvar(vendedorDTO.toVendedor());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vendedor> atualizar(@PathVariable Long id, @RequestBody Vendedor vendedorAtualizado) {
        Vendedor vendedorAtualizadoResult = vendedorService.atualizar(id, vendedorAtualizado);
        return ResponseEntity.ok(vendedorAtualizadoResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vendedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
