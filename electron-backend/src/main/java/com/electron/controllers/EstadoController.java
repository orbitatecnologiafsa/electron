package com.electron.controllers;

import com.electron.domain.Estado;
import com.electron.domain.Fornecedor;
import com.electron.services.EstadoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EstadoController {
    private EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> lisarTodos(){
        List<Estado> estados = estadoService.listarTodos();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> listarPorId(@PathVariable Long id){
        Estado estado = estadoService.listarPorId(id);
        return ResponseEntity.ok(estado);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Estado estado){
        estadoService.criar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //TODO
//    @PutMapping("/{id}")
//    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
//        return ResponseEntity.ok();
//    }

}
