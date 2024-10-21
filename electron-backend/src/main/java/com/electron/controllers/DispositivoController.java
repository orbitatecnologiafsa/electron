package com.electron.controllers;

import com.electron.domain.Dispositivo;
import com.electron.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoController {
    private final DispositivoService dispositivoService;

    @Autowired
    public DispositivoController(DispositivoService dispositivoService) {
        this.dispositivoService = dispositivoService;
    }

    @GetMapping
    public List<Dispositivo> listarTodos() {
        return dispositivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> listarPorId(@PathVariable Long id) {
        Dispositivo dispositivo = dispositivoService.listarPorId(id);
        return ResponseEntity.ok(dispositivo);
    }

    @PostMapping
    public Dispositivo criar(@RequestBody Dispositivo dispositivo) {
        return dispositivoService.criar(dispositivo);
    }

    @PutMapping("/{id}")
    public Dispositivo atualizar(@PathVariable Long id, @RequestBody Dispositivo dispositivo) {
        return dispositivoService.atualizar(id, dispositivo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        dispositivoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
