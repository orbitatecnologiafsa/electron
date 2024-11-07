package com.electron.controllers;

import com.electron.domain.Municipio;
import com.electron.domain.dtos.MunicipioDTO;
import com.electron.services.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public List<Municipio> listarTodos() {
        return municipioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> listarPorId(@PathVariable Long id) {
        Municipio municipio = municipioService.listarPorId(id);
        return ResponseEntity.ok(municipio);
    }

    @PostMapping
    public Municipio criar(@RequestBody MunicipioDTO municipioDTO) {
        return municipioService.criar(municipioDTO.toMunicipio());
    }

    @PutMapping("/{id}")
    public Municipio atualizar(@PathVariable Long id, @RequestBody Municipio municipio) {
        return municipioService.atualizar(id, municipio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        municipioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

