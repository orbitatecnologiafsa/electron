package com.electron.services;

import com.electron.domain.Municipio;
import com.electron.repositories.MunicipioRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService {

    private MunicipioRepository municipioRepository;

    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    public List<Municipio> listarTodos() {
        return municipioRepository.findAll();
    }

    public Municipio buscarPorId(Long id) {
        var municipio = municipioRepository.findById(id).orElseThrow(() -> new NotFoundException("Municipio não encontrado"));
        return municipio;
    }

    public Municipio salvar(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    public Municipio atualizar(Long id, Municipio municipio) {
        var municipioVar = buscarPorId(id);

        municipioVar.setNome(municipio.getNome());
        municipioVar.setEstado(municipio.getEstado());
        municipioVar.setId(municipio.getId());

        return municipioRepository.save(municipioVar);
    }

    public void deletar(Long id) {
        if( id != null)
            municipioRepository.deleteById(id);
    }

}
