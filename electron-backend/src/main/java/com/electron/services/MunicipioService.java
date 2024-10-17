package com.electron.services;

import com.electron.domain.Fornecedor;
import com.electron.domain.Municipio;
import com.electron.domain.Vendedor;
import com.electron.repositories.MunicipioRepository;
import com.electron.repositories.VendedorRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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

    public Municipio listarPorId(Long id){
        return municipioRepository.findById(id).
                orElseThrow( () -> new NotFoundException("Não foi possível encontrar"));
    }

    public void criar(Municipio municipio) {
        try {
            municipioRepository.save(municipio);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //TODO
//    public void atualizar(Long id, Municipio municipio) {
//       Municipio municipioExistente = municipioRepository.findById(id).
//                orElseThrow( () -> new NotFoundException("Municipio não encontrado!"));
//        municipioExistente.setEstado(municipio.getEstado());
//        municipioExistente.setNome(municipio.getNome());
//    }

    public void deletar(Long id) {
        if(municipioRepository.findById(id).isPresent()) {
            municipioRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível excluir o municipio de id " + id);
    }
}