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

import com.electron.domain.dtos.OutrasInformacoesDTO;
import com.electron.mappers.OutrasInformacoesMapper;
import com.electron.services.OutrasInformacoesService;

@RestController
@RequestMapping("/outras-informacoes")
public class OutrasInformacoesController {
    private final OutrasInformacoesService outrasInformacoesService;
    private final OutrasInformacoesMapper outrasInformacoesMapper;

    public OutrasInformacoesController(OutrasInformacoesService outrasInformacoesService, 
                                     OutrasInformacoesMapper outrasInformacoesMapper) {
        this.outrasInformacoesService = outrasInformacoesService;
        this.outrasInformacoesMapper = outrasInformacoesMapper;
    }

    @GetMapping
    public ResponseEntity<List<OutrasInformacoesDTO>> listarTodos() {
        return ResponseEntity.ok(
            outrasInformacoesService.listarTodos().stream()
                .map(outrasInformacoesMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutrasInformacoesDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(outrasInformacoesMapper.toDTO(outrasInformacoesService.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<OutrasInformacoesDTO> criar(@RequestBody OutrasInformacoesDTO outrasInformacoesDTO) {
        var entidade = outrasInformacoesMapper.toEntity(outrasInformacoesDTO);
        var salvo = outrasInformacoesService.criar(entidade);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(outrasInformacoesMapper.toDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutrasInformacoesDTO> atualizar(@PathVariable Long id, 
                                                         @RequestBody OutrasInformacoesDTO outrasInformacoesDTO) {
        var entidade = outrasInformacoesMapper.toEntity(outrasInformacoesDTO);
        var atualizado = outrasInformacoesService.atualizar(id, entidade);
        return ResponseEntity.ok(outrasInformacoesMapper.toDTO(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        outrasInformacoesService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}