package com.electron.controllers;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.dtos.EmpresaProprietariaDTO;
import com.electron.services.EmpresaProprietariaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas-proprietarias")
public class EmpresaProprietariaController {

    private final EmpresaProprietariaService empresaProprietariaService;

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
    public ResponseEntity<String> criar(@RequestBody @Valid EmpresaProprietariaDTO empresaProprietariaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                errors.append(error.getDefaultMessage()).append(", ")
            );
            return ResponseEntity.badRequest().body(errors.toString());
        }

        empresaProprietariaService.criar(empresaProprietariaDTO.toEmpresaProprietaria());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid EmpresaProprietariaDTO empresaProprietariaDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error ->
                errors.append(error.getDefaultMessage()).append(", ")
            );
            return ResponseEntity.badRequest().body(errors.toString());
        }

        empresaProprietariaService.atualizar(id, empresaProprietariaDTO.toEmpresaProprietaria());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaProprietariaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
