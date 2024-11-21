package com.electron.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electron.domain.dtos.VendedorDTO;
import com.electron.mappers.VendedorMapper;
import com.electron.services.VendedorService;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;
    private final VendedorMapper vendedorMapper;

    public VendedorController(VendedorService vendedorService, VendedorMapper vendedorMapper) {
        this.vendedorService = vendedorService;
        this.vendedorMapper = vendedorMapper;
    }

    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listarTodos() {
        return ResponseEntity.ok(
            vendedorService.listarTodos().stream()
                .map(vendedorMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vendedorMapper.toDTO(vendedorService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> criar(@RequestBody VendedorDTO vendedorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(vendedorMapper.toDTO(vendedorService.salvar(vendedorMapper.toEntity(vendedorDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> atualizar(@PathVariable Long id, @RequestBody VendedorDTO vendedorDTO) {
        return ResponseEntity.ok(
            vendedorMapper.toDTO(vendedorService.atualizar(id, vendedorMapper.toEntity(vendedorDTO)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vendedorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
