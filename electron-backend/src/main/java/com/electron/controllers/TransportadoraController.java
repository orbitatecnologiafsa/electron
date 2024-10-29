package com.electron.controllers;

import com.electron.domain.Transportadora;
import com.electron.services.TransportadoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {

    private final TransportadoraService transportadoraService;

    public TransportadoraController(TransportadoraService transportadoraService) {
        this.transportadoraService = transportadoraService;
    }

    // Listar todas as transportadoras
    @GetMapping
    public ResponseEntity<List<Transportadora>> listarTodas() {
        List<Transportadora> transportadoras = transportadoraService.listarTodas();
        return ResponseEntity.ok(transportadoras);
    }

    // Buscar transportadora por ID
    @GetMapping("/{id}")
    public ResponseEntity<Transportadora> buscarPorId(@PathVariable Long id) {
        Transportadora transportadora = transportadoraService.buscarPorId(id);
        return ResponseEntity.ok(transportadora);
    }

    // Criar nova transportadora
    @PostMapping
    public ResponseEntity<Transportadora> criar(@RequestBody Transportadora transportadora) {
        Transportadora novaTransportadora = transportadoraService.salvar(transportadora);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTransportadora);
    }

    // Atualizar transportadora existente
    @PutMapping("/{id}")
    public ResponseEntity<Transportadora> atualizar(@PathVariable Long id, @RequestBody Transportadora transportadoraAtualizada) {
        Transportadora transportadora = transportadoraService.atualizar(id, transportadoraAtualizada);
        return ResponseEntity.ok(transportadora);
    }

    // Excluir transportadora por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        transportadoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}