package com.electron.controllers;

import com.electron.domain.Estado;
import com.electron.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listarTodos() {
        return ResponseEntity.ok(estadoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Estado> criar(@RequestBody Estado estado, UriComponentsBuilder uriBuilder) {
        var estadoSalvo = estadoService.salvar(estado);

        URI uri = uriBuilder.path("/estados/{id}")
                .buildAndExpand(estadoSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(estadoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        var estadoAtualizado = estadoService.atualizar(id, estado);
        return ResponseEntity.ok(estadoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
