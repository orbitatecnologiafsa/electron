package com.electron.services;

import com.electron.domain.TributoNcm;
import com.electron.repositories.TributoNCMRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TributoNCMService {

    private final TributoNCMRepository tributoNCMRepository;

    public TributoNCMService(TributoNCMRepository tributoNCMRepository) {
        this.tributoNCMRepository = tributoNCMRepository;
    }

    public Page<TributoNcm> listarTodos(Pageable pageable) {
        return tributoNCMRepository.findAll(pageable);
    }

    public TributoNcm listarPorId(Long id) {
        return tributoNCMRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tributo NCM não encontrado com o ID: " + id));
    }

    public TributoNcm criar(TributoNcm tributoNCM) {
        return tributoNCMRepository.save(tributoNCM);
    }

    public TributoNcm atualizar(Long id, TributoNcm tributoNCMAtualizado) {
        return tributoNCMRepository.findById(id)
                .map(tributoNCM -> {
                    tributoNCM.setCodigo(tributoNCMAtualizado.getCodigo());
                    tributoNCM.setDescricao(tributoNCMAtualizado.getDescricao());
                    return tributoNCMRepository.save(tributoNCM);
                })
                .orElseThrow(() -> new NotFoundException("Tributo NCM não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        if (!tributoNCMRepository.existsById(id)) {
            throw new NotFoundException("Tributo NCM não encontrado com o ID: " + id);
        }
        tributoNCMRepository.deleteById(id);
    }

}
