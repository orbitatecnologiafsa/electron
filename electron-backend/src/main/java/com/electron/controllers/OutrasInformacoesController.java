package com.electron.controllers;

import com.electron.domain.OutrasInformacoes;
import com.electron.services.OutrasInformacoesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outras-informacoes")
public class OutrasInformacoesController {

    private OutrasInformacoesService outrasInformacoesService;

    public OutrasInformacoesController(OutrasInformacoesService outrasInformacoesService) {
        this.outrasInformacoesService = outrasInformacoesService;
    }

    @GetMapping
    public ResponseEntity<List<OutrasInformacoes>> listarTodos() {
        return ResponseEntity.ok(outrasInformacoesService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutrasInformacoes> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(outrasInformacoesService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody OutrasInformacoes outrasInformacoes) {
        outrasInformacoesService.criar(outrasInformacoes);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody OutrasInformacoes outrasInformacoes) {
        outrasInformacoesService.atualizar(id, outrasInformacoes);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        outrasInformacoesService.deletar(id);
        return ResponseEntity.ok().build();
    }
}