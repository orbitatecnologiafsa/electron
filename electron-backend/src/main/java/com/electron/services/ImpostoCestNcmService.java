package com.electron.services;

import com.electron.domain.ImpostoCestNcm;
import com.electron.repositories.ImpostoCestNcmRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpostoCestNcmService {
    private final ImpostoCestNcmRepository impostoCestNcmRepository;

    public ImpostoCestNcmService(ImpostoCestNcmRepository impostoCestNcmRepository) {
        this.impostoCestNcmRepository = impostoCestNcmRepository;
    }

    // Listar todos os ImpostoCestNcm
    public List<ImpostoCestNcm> listarTodos() {
        return impostoCestNcmRepository.findAll();
    }

    // Buscar ImpostoCestNcm por ID
    public ImpostoCestNcm buscarPorId(Long id) {
        return impostoCestNcmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Imposto CEST/NC não encontrado com o ID: " + id));
    }

    // Criar novo ImpostoCestNcm
    public ImpostoCestNcm salvar(ImpostoCestNcm impostoCestNcm) {
        return impostoCestNcmRepository.save(impostoCestNcm);
    }

    // Atualizar ImpostoCestNcm existente
    public ImpostoCestNcm atualizar(Long id, ImpostoCestNcm impostoCestNcmAtualizado) {
        return impostoCestNcmRepository.findById(id)
                .map(impostoCestNcm -> {
                    impostoCestNcm.setCodigoCest(impostoCestNcmAtualizado.getCodigoCest());
                    impostoCestNcm.setCodigoNcm(impostoCestNcmAtualizado.getCodigoNcm());
                    impostoCestNcm.setDescricaoProduto(impostoCestNcmAtualizado.getDescricaoProduto());
                    return impostoCestNcmRepository.save(impostoCestNcm);
                })
                .orElseThrow(() -> new NotFoundException("Imposto CEST/NC não encontrado com o ID: " + id));
    }

    // Excluir ImpostoCestNcm por ID
    public void excluir(Long id) {
        if (!impostoCestNcmRepository.existsById(id)) {
            throw new NotFoundException("Imposto CEST/NC não encontrado com o ID: " + id);
        }
        impostoCestNcmRepository.deleteById(id);
    }

}
