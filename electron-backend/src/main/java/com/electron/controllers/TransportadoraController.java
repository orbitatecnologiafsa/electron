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

import com.electron.domain.dtos.TransportadoraDTO;
import com.electron.mappers.TransportadoraMapper;
import com.electron.services.TransportadoraService;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {
    private final TransportadoraService transportadoraService;
    private final TransportadoraMapper transportadoraMapper;

    public TransportadoraController(TransportadoraService transportadoraService, 
                                  TransportadoraMapper transportadoraMapper) {
        this.transportadoraService = transportadoraService;
        this.transportadoraMapper = transportadoraMapper;
    }

    @GetMapping
    public ResponseEntity<List<TransportadoraDTO>> listarTodas(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
            transportadoraService.listarTodas(pageable).getContent().stream()
                .map(transportadoraMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportadoraDTO> buscarPorId(@PathVariable Long id) {
        var transportadora = transportadoraService.buscarPorId(id);
        return ResponseEntity.ok(transportadoraMapper.toDTO(transportadora));
    }

    @PostMapping
    public ResponseEntity<TransportadoraDTO> criar(@RequestBody TransportadoraDTO transportadoraDTO) {
        var transportadora = transportadoraMapper.toEntity(transportadoraDTO);
        var novaTransportadora = transportadoraService.salvar(transportadora);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(transportadoraMapper.toDTO(novaTransportadora));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransportadoraDTO> atualizar(@PathVariable Long id, 
                                                      @RequestBody TransportadoraDTO transportadoraDTO) {
        var transportadora = transportadoraMapper.toEntity(transportadoraDTO);
        var transportadoraAtualizada = transportadoraService.atualizar(id, transportadora);
        return ResponseEntity.ok(transportadoraMapper.toDTO(transportadoraAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        transportadoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}