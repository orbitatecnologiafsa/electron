package com.electron.controllers;

import com.electron.domain.TributoNcm;
import com.electron.services.TributoNCMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tributo-ncm")
public class TributoNCMController {

    private final TributoNCMService tributoNCMService;

    public TributoNCMController(TributoNCMService tributoNCMService) {
        this.tributoNCMService = tributoNCMService;
    }

    @GetMapping
    public ResponseEntity<List<TributoNcm>> listarTodos() {
        List<TributoNcm> tributos = tributoNCMService.listarTodos();
        return ResponseEntity.ok(tributos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TributoNcm> listarPorId(@PathVariable Long id) {
        TributoNcm tributo = tributoNCMService.listarPorId(id);
        return ResponseEntity.ok(tributo);
    }

    @PostMapping
    public ResponseEntity<TributoNcm> criar(@RequestBody TributoNcm tributoNCM) {
        TributoNcm novoTributo = tributoNCMService.criar(tributoNCM);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoTributo);
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
