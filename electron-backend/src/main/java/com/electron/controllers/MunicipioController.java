package com.electron.controllers;

import com.electron.domain.Estado;
import com.electron.domain.Municipio;
import com.electron.services.MunicipioService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class MunicipioController {

    private MunicipioService municipioService;

    @GetMapping
    public ResponseEntity<List<Municipio>> listarTodos(){
        List<Municipio> municipios = municipioService.listarTodos();
        return ResponseEntity.ok(municipios);
    }

    @GetMapping
    public ResponseEntity<Municipio> listarPorId(@PathVariable Long id){
        Municipio municipio = municipioService.listarPorId(id);
        return ResponseEntity.ok(municipio);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Municipio municipio){
        municipioService.criar(municipio);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //TODO
    //@PutMapping

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        municipioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
