package com.electron.controllers;

import com.electron.domain.Pessoa;
import com.electron.domain.dtos.PessoaDTO;
import com.electron.services.PessoaService;
import com.electron.mappers.PessoaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaMapper pessoaMapper;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> listarTodas(Pageable pageable) {
        Page<Pessoa> pessoas = pessoaService.listarTodas(pageable);
        Page<PessoaDTO> pessoaDTOs = pessoas.map(pessoaMapper::toDTO);
        return ResponseEntity.ok(pessoaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> listarPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.listarPorId(id);
        PessoaDTO pessoaDTO = pessoaMapper.toDTO(pessoa);
        return ResponseEntity.ok(pessoaDTO);
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> criar(@RequestBody @Valid PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        pessoa = pessoaService.criar(pessoa);
        PessoaDTO createdPessoaDTO = pessoaMapper.toDTO(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPessoaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaMapper.toEntity(pessoaDTO);
        pessoa = pessoaService.atualizar(id, pessoa);
        PessoaDTO updatedPessoaDTO = pessoaMapper.toDTO(pessoa);
        return ResponseEntity.ok(updatedPessoaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
