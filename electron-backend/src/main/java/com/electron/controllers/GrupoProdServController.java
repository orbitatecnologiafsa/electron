package com.electron.controllers;

import com.electron.domain.GrupoProdServ;
import com.electron.services.GrupoProdServService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos-prod-serv")
public class GrupoProdServController {
    private final GrupoProdServService grupoProdServService;

    public GrupoProdServController(GrupoProdServService grupoProdServService) {
        this.grupoProdServService = grupoProdServService;
    }

    // Listar todos os grupos de produto/serviço
    @GetMapping
    public ResponseEntity<List<GrupoProdServ>> listarTodos() {
        List<GrupoProdServ> gruposProdServ = grupoProdServService.listarTodos();
        return ResponseEntity.ok(gruposProdServ);
    }

    // Buscar grupo de produto/serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<GrupoProdServ> buscarPorId(@PathVariable Long id) {
        GrupoProdServ grupoProdServ = grupoProdServService.buscarPorId(id);
        return ResponseEntity.ok(grupoProdServ);
    }

    // Criar novo grupo de produto/serviço
    @PostMapping
    public ResponseEntity<GrupoProdServ> criar(@RequestBody GrupoProdServ grupoProdServ) {
        GrupoProdServ novoGrupoProdServ = grupoProdServService.salvar(grupoProdServ);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoGrupoProdServ);
    }

    // Atualizar grupo de produto/serviço existente
    @PutMapping("/{id}")
    public ResponseEntity<GrupoProdServ> atualizar(@PathVariable Long id, @RequestBody GrupoProdServ grupoProdServAtualizado) {
        GrupoProdServ grupoProdServ = grupoProdServService.atualizar(id, grupoProdServAtualizado);
        return ResponseEntity.ok(grupoProdServ);
    }

    // Excluir grupo de produto/serviço por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        grupoProdServService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
