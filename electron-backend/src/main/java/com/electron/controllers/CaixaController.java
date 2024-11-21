package com.electron.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.electron.domain.dtos.CaixaDTO;
import com.electron.mappers.CaixaMapper;
import com.electron.services.CaixaService;

@RestController
@RequestMapping("/caixas")
public class CaixaController {
    private final CaixaService caixaService;
    private final CaixaMapper caixaMapper;

    public CaixaController(CaixaService caixaService, CaixaMapper caixaMapper) {
        this.caixaService = caixaService;
        this.caixaMapper = caixaMapper;
    }

    @GetMapping
    public ResponseEntity<List<CaixaDTO>> listarPageable(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                caixaService.listarTodos(pageable).getContent().stream()
                        .map(caixaMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaixaDTO> listarPorId(@PathVariable Long id) {
        var caixa = caixaService.listarPorId(id);
        return ResponseEntity.ok(caixaMapper.toDTO(caixa));
    }

    @PostMapping
    public ResponseEntity<CaixaDTO> criar(@RequestBody CaixaDTO caixaDTO) {
        var caixa = caixaMapper.toEntity(caixaDTO);
        var novaCaixa = caixaService.criar(caixa);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(caixaMapper.toDTO(novaCaixa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaixaDTO> atualizar(@PathVariable Long id, @RequestBody CaixaDTO caixaDTO) {
        var caixa = caixaMapper.toEntity(caixaDTO);
        var caixaAtualizada = caixaService.atualizar(id, caixa);
        return ResponseEntity.ok(caixaMapper.toDTO(caixaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        caixaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}