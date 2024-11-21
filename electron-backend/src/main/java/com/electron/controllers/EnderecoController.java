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

import com.electron.domain.dtos.EnderecoDTO;
import com.electron.mappers.EnderecoMapper;
import com.electron.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;

    public EnderecoController(EnderecoService enderecoService, EnderecoMapper enderecoMapper) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> listarTodos() {
        var enderecos = enderecoService.listarTodos();
        return ResponseEntity.ok(
            enderecos.stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> listarPorId(@PathVariable Long id) {
        var endereco = enderecoService.listarPorId(id);
        return ResponseEntity.ok(enderecoMapper.toDTO(endereco));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criar(@RequestBody EnderecoDTO enderecoDTO) {
        var endereco = enderecoMapper.toEntity(enderecoDTO);
        var enderecoSalvo = enderecoService.criar(endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(enderecoMapper.toDTO(enderecoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        var endereco = enderecoMapper.toEntity(enderecoDTO);
        var enderecoAtualizado = enderecoService.atualizar(id, endereco);
        return ResponseEntity.ok(enderecoMapper.toDTO(enderecoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
