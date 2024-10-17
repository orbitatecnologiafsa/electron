package com.electron.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.electron.domain.Vendedor;
import com.electron.repositories.VendedorRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class VendedorService {
    private VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public Vendedor listarPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendedor não foi encontrado!"));
    }

    public void criar(Vendedor vendedor) {
        try {
            vendedor.setAtivo(vendedor.getAtivo() != null ? vendedor.getAtivo() : true);
            vendedorRepository.save(vendedor);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK9ilieyd1wui4hh2uiixixdk7m")) {
                throw new RuntimeException("Email já está em uso. Por favor, use um email diferente.", e);
            }
            throw e;
        }
    }

    public Vendedor atualizar(Long id, Vendedor vendedor) {
        Vendedor vendedorExistente = vendedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendedor não foi encontrado!"));
        
        // Atualizar todos os campos herdados de Pessoa
        vendedorExistente.setCpfCnpj(vendedor.getCpfCnpj());
        vendedorExistente.setNomeRazao(vendedor.getNomeRazao());
        vendedorExistente.setFantasia(vendedor.getFantasia());
        vendedorExistente.setRgInscricaoEstadual(vendedor.getRgInscricaoEstadual());
        vendedorExistente.setInscricaoEstadualMunicipal(vendedor.getInscricaoEstadualMunicipal());
        vendedorExistente.setContato(vendedor.getContato());
        vendedorExistente.setCep(vendedor.getCep());
        vendedorExistente.setLogradouro(vendedor.getLogradouro());
        vendedorExistente.setNumero(vendedor.getNumero());
        vendedorExistente.setBairro(vendedor.getBairro());
        vendedorExistente.setComplemento(vendedor.getComplemento());
        vendedorExistente.setUf(vendedor.getUf());
        vendedorExistente.setMunicipio(vendedor.getMunicipio());
        vendedorExistente.setTelefone(vendedor.getTelefone());
        vendedorExistente.setCelular(vendedor.getCelular());
        vendedorExistente.setEmail(vendedor.getEmail());
        vendedorExistente.setObservacao(vendedor.getObservacao());
        vendedorExistente.setAtivo(vendedor.getAtivo());
        
        // Atualizar campos específicos de Vendedor
        vendedorExistente.setNascimento(vendedor.getNascimento());
        vendedorExistente.setDesconto(vendedor.getDesconto());
        vendedorExistente.setComissao(vendedor.getComissao());
        vendedorExistente.setTipoComissao(vendedor.getTipoComissao());
        vendedorExistente.setBaseCalculo(vendedor.getBaseCalculo());
        
        return vendedorRepository.save(vendedorExistente);
    }

    public void deletar(Long id) {
        if(vendedorRepository.findById(id).isPresent() ){
            vendedorRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível achar o vendedor de id" + id);
    }
}