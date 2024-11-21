package com.electron.services;

import com.electron.domain.Produto;
import com.electron.repositories.ProdutoRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> listarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Produto buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado com ID: " + id));
    }

    public Produto salvar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        if (produto.getCodigo() == null || produto.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Código do produto é obrigatório");
        }
        if (produtoRepository.findByCodigo(produto.getCodigo()).isPresent()) {
            throw new AlreadyExistException("Já existe um produto com este código: " + produto.getCodigo());
        }
        return produtoRepository.save(produto);
    }

    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produto) {
        if (id == null || produto == null) {
            throw new IllegalArgumentException("ID e produto não podem ser nulos");
        }
        
        Produto existente = buscarPorId(id);
        
        if (!existente.getCodigo().equals(produto.getCodigo()) && 
            produtoRepository.findByCodigo(produto.getCodigo()).isPresent()) {
            throw new AlreadyExistException("Código já está em uso: " + produto.getCodigo());
        }
        
        // Atualiza todos os campos
        BeanUtils.copyProperties(produto, existente, "id");
        return produtoRepository.save(existente);
    }

}
