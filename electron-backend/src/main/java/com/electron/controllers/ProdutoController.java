package com.electron.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.Produto;
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

import com.electron.domain.dtos.ProdutoDTO;
import com.electron.mappers.ProdutoMapper;
import com.electron.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    public ProdutoController(ProdutoService produtoService, ProdutoMapper produtoMapper) {
        this.produtoService = produtoService;
        this.produtoMapper = produtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
            produtoService.listarTodos(pageable).getContent().stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoMapper.toDTO(produtoService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody @Valid  ProdutoDTO produtoDTO, UriComponentsBuilder uriBuilder) {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        Produto produtoSalvo = produtoService.salvar(produto);
        
        URI uri = uriBuilder.path("/produtos/{id}")
            .buildAndExpand(produtoSalvo.getId())
            .toUri();
        
        return ResponseEntity.created(uri).body(produtoMapper.toDTO(produtoSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return ResponseEntity.ok(
            produtoMapper.toDTO(produtoService.atualizar(id, produtoMapper.toEntity(produtoDTO)))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
