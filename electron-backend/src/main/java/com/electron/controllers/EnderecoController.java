package com.electron.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.Endereco;
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
    public ResponseEntity<List<EnderecoDTO>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
            enderecoService.listarTodos(pageable).getContent().stream()
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
    public ResponseEntity<EnderecoDTO> criar(@RequestBody @Valid EnderecoDTO enderecoDTO, UriComponentsBuilder uriBuilder) {
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        Endereco enderecoSalvo = enderecoService.criar(endereco);
        
        URI uri = uriBuilder.path("/enderecos/{id}")
            .buildAndExpand(enderecoSalvo.getId())
            .toUri();
            
        return ResponseEntity.created(uri).body(enderecoMapper.toDTO(enderecoSalvo));
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
