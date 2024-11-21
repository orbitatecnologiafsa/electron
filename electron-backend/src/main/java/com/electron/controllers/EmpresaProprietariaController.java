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

import com.electron.domain.dtos.EmpresaProprietariaDTO;
import com.electron.mappers.EmpresaProprietariaMapper;
import com.electron.services.EmpresaProprietariaService;

@RestController
@RequestMapping("/empresas-proprietarias")
public class EmpresaProprietariaController {
    private final EmpresaProprietariaService empresaProprietariaService;
    private final EmpresaProprietariaMapper empresaProprietariaMapper;

    public EmpresaProprietariaController(EmpresaProprietariaService empresaProprietariaService, 
                                       EmpresaProprietariaMapper empresaProprietariaMapper) {
        this.empresaProprietariaService = empresaProprietariaService;
        this.empresaProprietariaMapper = empresaProprietariaMapper;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaProprietariaDTO>> listarTodas() {
        var empresas = empresaProprietariaService.listarTodas();
        return ResponseEntity.ok(
            empresas.stream()
                .map(empresaProprietariaMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProprietariaDTO> listarPorId(@PathVariable Long id) {
        var empresa = empresaProprietariaService.listarPorId(id);
        return ResponseEntity.ok(empresaProprietariaMapper.toDTO(empresa));
    }

    @PostMapping
    public ResponseEntity<EmpresaProprietariaDTO> criar(@RequestBody EmpresaProprietariaDTO empresaProprietariaDTO) {
        var empresa = empresaProprietariaMapper.toEntity(empresaProprietariaDTO);
        var empresaSalva = empresaProprietariaService.criar(empresa);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(empresaProprietariaMapper.toDTO(empresaSalva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaProprietariaDTO> atualizar(@PathVariable Long id, 
                                                           @RequestBody EmpresaProprietariaDTO empresaProprietariaDTO) {
        var empresa = empresaProprietariaMapper.toEntity(empresaProprietariaDTO);
        var empresaAtualizada = empresaProprietariaService.atualizar(id, empresa);
        return ResponseEntity.ok(empresaProprietariaMapper.toDTO(empresaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaProprietariaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}