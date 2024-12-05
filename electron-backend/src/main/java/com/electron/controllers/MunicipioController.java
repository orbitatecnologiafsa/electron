package com.electron.controllers;

import com.electron.domain.dtos.MunicipioDTO;
import com.electron.services.MunicipioService;
import com.electron.mappers.MunicipioMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    private final MunicipioService municipioService;
    private final MunicipioMapper municipioMapper;

    public MunicipioController(MunicipioService municipioService, MunicipioMapper municipioMapper) {
        this.municipioService = municipioService;
        this.municipioMapper = municipioMapper;
    }

    @GetMapping
    public ResponseEntity<List<MunicipioDTO>> listarTodos() {
        var municipios = municipioService.listarTodos();
        var municipiosDTO = municipios.stream()
                                      .map(municipioMapper::toDTO)
                                      .collect(Collectors.toList());
        return ResponseEntity.ok(municipiosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDTO> listarPorId(@PathVariable Long id) {
        var municipio = municipioService.buscarPorId(id);
        return ResponseEntity.ok(municipioMapper.toDTO(municipio));
    }

    @PostMapping
    public ResponseEntity<MunicipioDTO> criar(@RequestBody MunicipioDTO municipioDTO, UriComponentsBuilder uriBuilder) {
        var municipio = municipioMapper.toEntity(municipioDTO);
        var municipioSalvo = municipioService.salvar(municipio);

        URI uri = uriBuilder.path("/municipios/{id}")
                .buildAndExpand(municipioSalvo.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(municipioMapper.toDTO(municipioSalvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipioDTO> atualizar(@PathVariable Long id, @RequestBody MunicipioDTO municipioDTO) {
        var municipioAtualizado = municipioService.atualizar(id, municipioMapper.toEntity(municipioDTO));
        return ResponseEntity.ok(municipioMapper.toDTO(municipioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        municipioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
