package com.electron.services;

import com.electron.domain.TributoCest;
import com.electron.repositories.TributoCESTRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TributoCESTService {

    private final TributoCESTRepository tributoCESTRepository;

    public TributoCESTService(TributoCESTRepository tributoCESTRepository) {
        this.tributoCESTRepository = tributoCESTRepository;
    }

    public List<TributoCest> listarTodos() {
        return tributoCESTRepository.findAll();
    }

    public TributoCest listarPorId(Long id) {
        return tributoCESTRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tributo CEST não encontrado com o ID: " + id));
    }

    public TributoCest criar(TributoCest tributoCEST) {
        return tributoCESTRepository.save(tributoCEST);
    }

    public TributoCest atualizar(Long id, TributoCest tributoCESTAtualizado) {
        return tributoCESTRepository.findById(id)
                .map(tributoCEST -> {
                    tributoCEST.setCodigo(tributoCESTAtualizado.getCodigo());
                    tributoCEST.setDescricao(tributoCESTAtualizado.getDescricao());
                    tributoCEST.setTributoNcm(tributoCESTAtualizado.getTributoNcm());
                    return tributoCESTRepository.save(tributoCEST);
                })
                .orElseThrow(() -> new NotFoundException("Tributo CEST não encontrado com o ID: " + id));
    }

    public void deletar(Long id) {
        if (!tributoCESTRepository.existsById(id)) {
            throw new NotFoundException("Tributo CEST não encontrado com o ID: " + id);
        }
        tributoCESTRepository.deleteById(id);
    }
}
