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

import com.electron.domain.dtos.DispositivoDTO;
import com.electron.mappers.DispositivoMapper;
import com.electron.services.DispositivoService;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {
    private final DispositivoService dispositivoService;
    private final DispositivoMapper dispositivoMapper;

    public DispositivoController(DispositivoService dispositivoService, DispositivoMapper dispositivoMapper) {
        this.dispositivoService = dispositivoService;
        this.dispositivoMapper = dispositivoMapper;
    }

    @GetMapping
    public ResponseEntity<List<DispositivoDTO>> listarTodos() {
        return ResponseEntity.ok(
            dispositivoService.listarTodos().stream()
                .map(dispositivoMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dispositivoMapper.toDTO(dispositivoService.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<DispositivoDTO> criar(@RequestBody DispositivoDTO dispositivoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(dispositivoMapper.toDTO(dispositivoService.criar(dispositivoMapper.toEntity(dispositivoDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DispositivoDTO> atualizar(@PathVariable Long id, @RequestBody DispositivoDTO dispositivoDTO) {
        return ResponseEntity.ok(
            dispositivoMapper.toDTO(dispositivoService.atualizar(id, dispositivoMapper.toEntity(dispositivoDTO)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        dispositivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
