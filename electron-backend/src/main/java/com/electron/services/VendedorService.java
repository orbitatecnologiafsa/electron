package com.electron.services;

import com.electron.domain.Vendedor;
import com.electron.repositories.VendedorRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public Optional<Vendedor> buscarPorId(Long id) {
        return vendedorRepository.findById(id);
    }

    // Salvar um novo vendedor
    public Vendedor salvar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    // Atualizar um vendedor existente
    public Vendedor atualizar(Long id, Vendedor vendedorAtualizado) {
        return vendedorRepository.findById(id)
                .map(vendedor -> {
                    vendedor.setPessoa(vendedorAtualizado.getPessoa());
                    vendedor.setDesconto(vendedorAtualizado.getDesconto());
                    vendedor.setComissao(vendedorAtualizado.getComissao());
                    vendedor.setTipoComissao(vendedorAtualizado.getTipoComissao());
                    vendedor.setBaseCalculo(vendedorAtualizado.getBaseCalculo());
                    vendedor.setEmpresa(vendedorAtualizado.getEmpresa());
                    vendedor.setObservacoes(vendedorAtualizado.getObservacoes());
                    return vendedorRepository.save(vendedor);
                })
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado com o ID: " + id));
    }

    // Excluir um vendedor por ID
    public void excluir(Long id) {
        if (vendedorRepository.existsById(id)) {
            vendedorRepository.deleteById(id);
        } else {
            throw new NotFoundException("Vendedor não encontrado com o ID: " + id);
        }
    }

}
