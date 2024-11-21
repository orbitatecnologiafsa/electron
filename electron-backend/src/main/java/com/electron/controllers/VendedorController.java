package com.electron.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.dtos.CaixaDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<VendedorDTO>> listarPageable(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                vendedorService.listarTodos(pageable).getContent().stream()
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
