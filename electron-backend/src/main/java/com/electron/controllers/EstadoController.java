package com.electron.controllers;

import com.electron.domain.Estado;
import com.electron.domain.dtos.EstadoDTO;
import com.electron.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<Estado> listarTodos() {
        return estadoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> listarPorId(@PathVariable Long id) {
        Estado estado = estadoService.listarPorId(id);
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public Estado criar(@RequestBody EstadoDTO estadoDTO) {
        return estadoService.criar(estadoDTO.toEstado());
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        return estadoService.atualizar(id, estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}