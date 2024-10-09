package com.electron.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electron.domain.Registro;
import com.electron.repositories.RegistroRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class RegistroService {
    private RegistroRepository registroRepository;

    public RegistroService(RegistroRepository registroRepository) {
        this.registroRepository = registroRepository;
    }

    public List<Registro> listarTodos() {
        return registroRepository.findAll();
    }

    public Registro listarPorId(Long id) {
        return registroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro não foi encontrado!"));
    }

    public void criar(Registro registro) {
        registroRepository.save(registro);
    }

    public Registro atualizar(Long id, Registro registro) {
        Registro registroExistente = registroRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Registro não foi encontrado!"));
        
        registroExistente.setCpfCnpj(registro.getCpfCnpj());
        registroExistente.setNomeRazao(registro.getNomeRazao());
        registroExistente.setFantasia(registro.getFantasia());
        registroExistente.setRgInscricaoEstadual(registro.getRgInscricaoEstadual());
        registroExistente.setInscricaoEstadualMunicipal(registro.getInscricaoEstadualMunicipal());
        registroExistente.setContato(registro.getContato());
        registroExistente.setCep(registro.getCep());
        registroExistente.setLogradouro(registro.getLogradouro());
        registroExistente.setNumero(registro.getNumero());
        registroExistente.setBairro(registro.getBairro());
        registroExistente.setComplemento(registro.getComplemento());
        registroExistente.setUf(registro.getUf());
        registroExistente.setMunicipio(registro.getMunicipio());
        registroExistente.setTelefone(registro.getTelefone());
        registroExistente.setCelular(registro.getCelular());
        registroExistente.setEmail(registro.getEmail());
        registroExistente.setObservacao(registro.getObservacao());
        registroExistente.setAtivo(registro.getAtivo());
        
        return registroRepository.save(registroExistente);
    }

    public void deletar(Long id) {
        registroRepository.deleteById(id);
    }
}