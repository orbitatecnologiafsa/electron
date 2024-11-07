package com.electron.controllers;

import com.electron.domain.Caixa;
import com.electron.domain.dtos.CaixaDTO;
import com.electron.services.CaixaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caixas")
public class CaixaController {

    private CaixaService caixaService;

    public CaixaController(CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    @GetMapping
    public ResponseEntity<List<Caixa>> listarTodos() {
        return ResponseEntity.ok(caixaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caixa> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(caixaService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody CaixaDTO caixaDTO) {
        caixaService.criar(caixaDTO.toCaixa());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody Caixa caixa) {
        caixaService.atualizar(id, caixa);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        caixaService.delete(id);
        return ResponseEntity.ok().build();
    }
}