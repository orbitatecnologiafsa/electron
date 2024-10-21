package com.electron.services;

import com.electron.domain.Municipio;
import com.electron.repositories.MunicipioRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {
    private final MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public List<Municipio> listarTodos() {
        return municipioRepository.findAll();
    }

    public Municipio listarPorId(Long id) {
        return municipioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado!"));
    }

    public Municipio criar(Municipio municipio) {
        try {
            return municipioRepository.save(municipio);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao criar município. Verifique os dados.", e);
        }
    }

    public Municipio atualizar(Long id, Municipio municipio) {
        Municipio municipioExistente = municipioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Município não encontrado!"));

        municipioExistente.setNome(municipio.getNome());
        municipioExistente.setEstado(municipio.getEstado());

        return municipioRepository.save(municipioExistente);
    }

    public void deletar(Long id) {
        if (municipioRepository.findById(id).isPresent()) {
            municipioRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível deletar o município de id " + id);
    }
}
