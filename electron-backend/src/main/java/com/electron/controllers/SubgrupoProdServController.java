package com.electron.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.electron.domain.dtos.SubgrupoProdServDTO;
import com.electron.mappers.SubgrupoProdServMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.electron.services.SubgrupoProdServService;

@RestController
@RequestMapping("/subgrupos-prod-serv")
public class SubgrupoProdServController {
    private final SubgrupoProdServService subgrupoProdServService;
    private final SubgrupoProdServMapper subgrupoProdServMapper;

    public SubgrupoProdServController(SubgrupoProdServService subgrupoProdServService,
                                      SubgrupoProdServMapper subgrupoProdServMapper) {
        this.subgrupoProdServService = subgrupoProdServService;
        this.subgrupoProdServMapper = subgrupoProdServMapper;
    }

    @GetMapping
    public ResponseEntity<List<SubgrupoProdServDTO>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(
                subgrupoProdServService.listarTodos(pageable).getContent().stream()
                        .map(subgrupoProdServMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubgrupoProdServDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(subgrupoProdServMapper.toDTO(subgrupoProdServService.buscarPorId(id)));
    }

    @PostMapping
    public ResponseEntity<SubgrupoProdServDTO> criar(@RequestBody @Valid SubgrupoProdServDTO dto, 
                                                    UriComponentsBuilder uriBuilder) {
        var entity = subgrupoProdServMapper.toEntity(dto);
        var saved = subgrupoProdServService.salvar(entity);
        
        URI uri = uriBuilder.path("/subgrupos-prod-serv/{id}")
            .buildAndExpand(saved.getId())
            .toUri();
            
        return ResponseEntity.created(uri).body(subgrupoProdServMapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubgrupoProdServDTO> atualizar(@PathVariable Long id, 
                                                        @RequestBody @Valid SubgrupoProdServDTO dto) {
        var entity = subgrupoProdServMapper.toEntity(dto);
        var updated = subgrupoProdServService.atualizar(id, entity);
        return ResponseEntity.ok(subgrupoProdServMapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        subgrupoProdServService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
