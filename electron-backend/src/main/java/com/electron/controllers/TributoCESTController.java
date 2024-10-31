package com.electron.controllers;

import com.electron.domain.TributoCest;
import com.electron.services.TributoCESTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tributo-cest")
public class TributoCESTController {
    private final TributoCESTService tributoCESTService;

    public TributoCESTController(TributoCESTService tributoCESTService) {
        this.tributoCESTService = tributoCESTService;
    }

    @GetMapping
    public ResponseEntity<List<TributoCest>> listarTodos() {
        List<TributoCest> tributos = tributoCESTService.listarTodos();
        return ResponseEntity.ok(tributos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TributoCest> listarPorId(@PathVariable Long id) {
        TributoCest tributo = tributoCESTService.listarPorId(id);
        return ResponseEntity.ok(tributo);
    }

    @PostMapping
    public ResponseEntity<TributoCest> criar(@RequestBody TributoCest tributoCEST) {
        TributoCest novoTributo = tributoCESTService.criar(tributoCEST);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTributo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TributoCest> atualizar(@PathVariable Long id, @RequestBody TributoCest tributoCESTAtualizado) {
        TributoCest tributoAtualizado = tributoCESTService.atualizar(id, tributoCESTAtualizado);
        return ResponseEntity.ok(tributoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tributoCESTService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
