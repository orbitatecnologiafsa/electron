package com.electron.controllers;

import java.net.URI;
import java.util.List;

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

import com.electron.domain.TributoNcm;
import com.electron.services.TributoNCMService;

@RestController
@RequestMapping("/tributo-ncm")
public class TributoNCMController {

    private final TributoNCMService tributoNCMService;

    public TributoNCMController(TributoNCMService tributoNCMService) {
        this.tributoNCMService = tributoNCMService;
    }

    @GetMapping
    public ResponseEntity<List<TributoNcm>> listarTodos(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(tributoNCMService.listarTodos(pageable).getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TributoNcm> listarPorId(@PathVariable Long id) {
        TributoNcm tributo = tributoNCMService.listarPorId(id);
        return ResponseEntity.ok(tributo);
    }

    @PostMapping
    public ResponseEntity<TributoNcm> criar(@RequestBody @Valid TributoNcm tributoNcm, UriComponentsBuilder uriBuilder) {
        TributoNcm tributoSalvo = tributoNCMService.criar(tributoNcm);
        
        URI uri = uriBuilder.path("/tributos-ncm/{id}")
            .buildAndExpand(tributoSalvo.getId())
            .toUri();
            
        return ResponseEntity.created(uri).body(tributoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TributoNcm> atualizar(@PathVariable Long id, @RequestBody TributoNcm tributoNCMAtualizado) {
        TributoNcm tributoAtualizado = tributoNCMService.atualizar(id, tributoNCMAtualizado);
        return ResponseEntity.ok(tributoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tributoNCMService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
