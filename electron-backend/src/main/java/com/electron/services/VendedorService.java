package com.electron.services;

import com.electron.domain.Pessoa;
import com.electron.domain.Vendedor;
import com.electron.repositories.PessoaRepository;
import com.electron.repositories.VendedorRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;
    private final PessoaRepository pessoaRepository;

    public VendedorService(VendedorRepository vendedorRepository, PessoaRepository pessoaRepository) {
        this.vendedorRepository = vendedorRepository;
        this.pessoaRepository = pessoaRepository;
    }

    // Listar todos os vendedores
    public List<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    // Buscar vendedor por ID
    public Vendedor buscarPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado com o ID: " + id));
    }

    // Salvar um novo vendedor com validações de unicidade em Pessoa
    public Vendedor salvar(Vendedor vendedor) {
        validarPessoaUnicidade(vendedor.getPessoa());
        return vendedorRepository.save(vendedor);
    }

    // Atualizar um vendedor existente com validações de unicidade em Pessoa
    public Vendedor atualizar(Long id, Vendedor vendedorAtualizado) {
        Vendedor vendedorExistente = buscarPorId(id); // Lança exceção se não encontrar
        validarPessoaUnicidade(vendedorAtualizado.getPessoa());

        vendedorExistente.setPessoa(vendedorAtualizado.getPessoa());
        vendedorExistente.setDesconto(vendedorAtualizado.getDesconto());
        vendedorExistente.setComissao(vendedorAtualizado.getComissao());
        vendedorExistente.setTipoComissao(vendedorAtualizado.getTipoComissao());
        vendedorExistente.setBaseCalculo(vendedorAtualizado.getBaseCalculo());
        vendedorExistente.setObservacoes(vendedorAtualizado.getObservacoes());

        return vendedorRepository.save(vendedorExistente);
    }

    // Excluir vendedor por ID
    public void excluir(Long id) {
        if (!vendedorRepository.existsById(id)) {
            throw new NotFoundException("Vendedor não encontrado com o ID: " + id);
        }
        vendedorRepository.deleteById(id);
    }

    // Validação para garantir unicidade de email e cpfCnpj na entidade Pessoa
    private void validarPessoaUnicidade(Pessoa pessoa) {
        if (pessoaRepository.findByEmail(pessoa.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + pessoa.getEmail());
        }
        if (pessoaRepository.findByCpfCnpj(pessoa.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + pessoa.getCpfCnpj());
        }
    }

}
