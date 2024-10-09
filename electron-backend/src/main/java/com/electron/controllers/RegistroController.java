package com.electron.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electron.domain.Registro;
import com.electron.services.RegistroService;

@RestController
@RequestMapping("/api/registros")
public class RegistroController {

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public ResponseEntity<List<Registro>> listarTodos() {
        return ResponseEntity.ok(registroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(registroService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Registro registro) {
        registroService.criar(registro);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registro> atualizar(@PathVariable Long id, @RequestBody Registro registro) {
        return ResponseEntity.ok(registroService.atualizar(id, registro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        registroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}