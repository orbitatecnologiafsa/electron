package com.electron.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.Dispositivo;
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
    public ResponseEntity<List<DispositivoDTO>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
            dispositivoService.listarTodos(pageable).getContent().stream()
                .map(dispositivoMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositivoDTO> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(dispositivoMapper.toDTO(dispositivoService.listarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<DispositivoDTO> criar(@RequestBody @Valid DispositivoDTO dispositivoDTO, UriComponentsBuilder uriBuilder) {
        Dispositivo dispositivo = dispositivoMapper.toEntity(dispositivoDTO);
        Dispositivo dispositivoSalvo = dispositivoService.criar(dispositivo);
        
        URI uri = uriBuilder.path("/dispositivos/{id}")
            .buildAndExpand(dispositivoSalvo.getId())
            .toUri();
            
        return ResponseEntity.created(uri).body(dispositivoMapper.toDTO(dispositivoSalvo));
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
