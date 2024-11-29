package com.electron.services;

import com.electron.domain.Pessoa;
import com.electron.repositories.PessoaRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Page<Pessoa> listarTodas(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada com ID: " + id));
    }

    public Pessoa criar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new IllegalArgumentException("Pessoa não pode ser nula");
        }
        if (pessoa.getCpfCnpj() == null || pessoa.getCpfCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório");
        }
        if (pessoaRepository.findByCpfCnpj(pessoa.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("Já existe uma pessoa com este CPF/CNPJ: " + pessoa.getCpfCnpj());
        }
        if (pessoaRepository.findByEmail(pessoa.getEmail()).isPresent()) {
            throw new AlreadyExistException("Já existe uma pessoa com este email: " + pessoa.getEmail());
        }
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(Long id, Pessoa pessoa) {
        if (id == null || pessoa == null) {
            throw new IllegalArgumentException("ID e pessoa não podem ser nulos");
        }

        Pessoa existente = listarPorId(id);

        if (!existente.getCpfCnpj().equals(pessoa.getCpfCnpj()) &&
            pessoaRepository.findByCpfCnpj(pessoa.getCpfCnpj()).isPresent()) {
            throw new AlreadyExistException("Já existe uma pessoa com este CPF/CNPJ: " + pessoa.getCpfCnpj());
        }

        if (!existente.getEmail().equals(pessoa.getEmail()) &&
            pessoaRepository.findByEmail(pessoa.getEmail()).isPresent()) {
            throw new AlreadyExistException("Já existe uma pessoa com este email: " + pessoa.getEmail());
        }

        BeanUtils.copyProperties(pessoa, existente, "id");
        return pessoaRepository.save(existente);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!pessoaRepository.existsById(id)) {
            throw new NotFoundException("Pessoa não encontrada com ID: " + id);
        }
        pessoaRepository.deleteById(id);
    }
}
