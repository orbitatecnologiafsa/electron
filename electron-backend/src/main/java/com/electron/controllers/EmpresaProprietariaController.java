package com.electron.controllers;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.dtos.EmpresaProprietariaDTO;
import com.electron.services.EmpresaProprietariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas-proprietarias")
public class EmpresaProprietariaController {

    private EmpresaProprietariaService empresaProprietariaService;

    public EmpresaProprietariaController(EmpresaProprietariaService empresaProprietariaService) {
        this.empresaProprietariaService = empresaProprietariaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaProprietaria>> listarTodas() {
        return ResponseEntity.ok(empresaProprietariaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaProprietaria> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaProprietariaService.listarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody EmpresaProprietaria empresaProprietaria) {
        empresaProprietariaService.criar(empresaProprietaria);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody EmpresaProprietaria empresaProprietaria) {
        empresaProprietariaService.atualizar(id, empresaProprietaria);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaProprietariaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}