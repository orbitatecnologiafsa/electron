package com.electron.controllers;

import com.electron.domain.Transportadora;
import com.electron.services.TransportadoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportadoras")
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    public TransportadoraController(TransportadoraService transportadoraService) {
        this.transportadoraService = transportadoraService;
    }

    @GetMapping
    public ResponseEntity<List<Transportadora>> listarTodas() {
        return ResponseEntity.ok(transportadoraService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportadora> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(transportadoraService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Transportadora transportadora) {
        transportadoraService.criar(transportadora);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transportadora> atualizar(@PathVariable Long id, @RequestBody Transportadora transportadora) {
        return ResponseEntity.ok(transportadoraService.atualizar(id, transportadora));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        transportadoraService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}