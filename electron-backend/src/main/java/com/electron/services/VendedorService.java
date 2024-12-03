package com.electron.services;

import com.electron.domain.Pessoa;
import com.electron.domain.Vendedor;
import com.electron.repositories.PessoaRepository;
import com.electron.repositories.VendedorRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Vendedor> listarTodos(Pageable pageable) {
        return vendedorRepository.findAll(pageable);
    }

    public Vendedor buscarPorId(Long id) {
        return vendedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vendedor não encontrado com o ID: " + id));
    }

    public Vendedor salvar(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor atualizar(Long id, Vendedor vendedorAtualizado) {
        Vendedor vendedorExistente = buscarPorId(id);

        vendedorExistente.setDesconto(vendedorAtualizado.getDesconto());
        vendedorExistente.setComissao(vendedorAtualizado.getComissao());
        vendedorExistente.setTipoComissao(vendedorAtualizado.getTipoComissao());
        vendedorExistente.setBaseCalculo(vendedorAtualizado.getBaseCalculo());
        vendedorExistente.setObservacoes(vendedorAtualizado.getObservacoes());
        vendedorExistente.setTipo(vendedorAtualizado.getTipo());
        vendedorExistente.setFoto(vendedorAtualizado.getFoto());
        vendedorExistente.setEntidade(vendedorExistente.getEntidade());
        vendedorExistente.setCpfCnpj(vendedorAtualizado.getCpfCnpj());
        vendedorExistente.setNomeRazaoSocial(vendedorAtualizado.getNomeRazaoSocial());
        vendedorExistente.setNomeFantasia(vendedorAtualizado.getNomeFantasia());
        vendedorExistente.setPassaporte(vendedorAtualizado.getPassaporte());
        vendedorExistente.setRgInscricaoEstadual(vendedorAtualizado.getRgInscricaoEstadual());
        vendedorExistente.setInscricaoMunicipal(vendedorAtualizado.getInscricaoMunicipal());
        vendedorExistente.setContato(vendedorAtualizado.getContato());
        vendedorExistente.setCep(vendedorAtualizado.getCep());
        vendedorExistente.setLogradouro(vendedorAtualizado.getLogradouro());
        vendedorExistente.setNumero(vendedorAtualizado.getNumero());
        vendedorExistente.setBairro(vendedorAtualizado.getBairro());
        vendedorExistente.setComplemento(vendedorAtualizado.getComplemento());
        vendedorExistente.setMunicipio(vendedorAtualizado.getMunicipio());
        vendedorExistente.setTelefone(vendedorAtualizado.getTelefone());
        vendedorExistente.setCelular(vendedorAtualizado.getCelular());
        vendedorExistente.setEmail(vendedorAtualizado.getEmail());
        vendedorExistente.setDataDeNascimento(vendedorAtualizado.getDataDeNascimento());

        return vendedorRepository.save(vendedorExistente);
    }

    public void excluir(Long id) {
        if (!vendedorRepository.existsById(id))
            throw new NotFoundException("Vendedor não encontrado com o ID: " + id);

        vendedorRepository.deleteById(id);
    }

    private void validarPessoaUnicidade(Pessoa pessoa) {
        if (pessoaRepository.findByEmail(pessoa.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email já está em uso: " + pessoa.getEmail());
        }
        if (pessoaRepository.findByCpfCnpj(pessoa.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("CPF/CNPJ já está em uso: " + pessoa.getCpfCnpj());
        }
    }

}
