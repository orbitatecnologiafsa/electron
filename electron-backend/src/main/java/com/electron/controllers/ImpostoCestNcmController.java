package com.electron.controllers;

import com.electron.domain.ImpostoCestNcm;
import com.electron.services.ImpostoCestNcmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/impostos-cest-ncm")
public class ImpostoCestNcmController {
    private final ImpostoCestNcmService impostoCestNcmService;

    public ImpostoCestNcmController(ImpostoCestNcmService impostoCestNcmService) {
        this.impostoCestNcmService = impostoCestNcmService;
    }

    // Listar todos os ImpostoCestNcm
    @GetMapping
    public ResponseEntity<List<ImpostoCestNcm>> listarTodos() {
        List<ImpostoCestNcm> impostosCestNcm = impostoCestNcmService.listarTodos();
        return ResponseEntity.ok(impostosCestNcm);
    }

    // Buscar ImpostoCestNcm por ID
    @GetMapping("/{id}")
    public ResponseEntity<ImpostoCestNcm> buscarPorId(@PathVariable Long id) {
        ImpostoCestNcm impostoCestNcm = impostoCestNcmService.buscarPorId(id);
        return ResponseEntity.ok(impostoCestNcm);
    }

    // Criar novo ImpostoCestNcm
    @PostMapping
    public ResponseEntity<ImpostoCestNcm> criar(@RequestBody ImpostoCestNcm impostoCestNcm) {
        ImpostoCestNcm novoImpostoCestNcm = impostoCestNcmService.salvar(impostoCestNcm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoImpostoCestNcm);
    }

    // Atualizar ImpostoCestNcm existente
    @PutMapping("/{id}")
    public ResponseEntity<ImpostoCestNcm> atualizar(@PathVariable Long id, @RequestBody ImpostoCestNcm impostoCestNcmAtualizado) {
        ImpostoCestNcm impostoCestNcm = impostoCestNcmService.atualizar(id, impostoCestNcmAtualizado);
        return ResponseEntity.ok(impostoCestNcm);
    }

    // Excluir ImpostoCestNcm por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        impostoCestNcmService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
