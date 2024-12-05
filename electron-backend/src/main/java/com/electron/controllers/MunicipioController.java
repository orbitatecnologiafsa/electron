package com.electron.controllers;

import com.electron.domain.Municipio;
import com.electron.domain.dtos.CaixaDTO;
import com.electron.services.MunicipioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    private MunicipioService municipioService;

    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping
    public ResponseEntity<List<Municipio>> listarTodos() {
        return ResponseEntity.ok(municipioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> listarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(municipioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Municipio> criar(@RequestBody Municipio municipio, UriComponentsBuilder uriBuilder) {
        var municipioVar = municipioService.salvar(municipio);

        URI uri = uriBuilder.path("/municipios/{id}")
                .buildAndExpand(municipioVar.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(municipioVar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> atualizar(@PathVariable Long id, @RequestBody Municipio municipio) {
        var municipioVar = municipioService.buscarPorId(id);
        var municipioNovo = municipioService.atualizar(id, municipioVar);
        return ResponseEntity.ok(municipioNovo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        municipioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
