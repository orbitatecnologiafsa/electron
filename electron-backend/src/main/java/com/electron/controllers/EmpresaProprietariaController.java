package com.electron.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.EmpresaProprietaria;
import jakarta.validation.Valid;
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
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<List<EmpresaProprietariaDTO>> listarTodas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
            empresaProprietariaService.listarTodas(pageable).getContent().stream()
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
    public ResponseEntity<EmpresaProprietariaDTO> criar(@RequestBody @Valid EmpresaProprietariaDTO empresaDTO, UriComponentsBuilder uriBuilder) {
        EmpresaProprietaria empresa = empresaProprietariaMapper.toEntity(empresaDTO);
        EmpresaProprietaria empresaSalva = empresaProprietariaService.criar(empresa);
        
        URI uri = uriBuilder.path("/empresas-proprietarias/{id}")
            .buildAndExpand(empresaSalva.getId())
            .toUri();
            
        return ResponseEntity.created(uri).body(empresaProprietariaMapper.toDTO(empresaSalva));
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