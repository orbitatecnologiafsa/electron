package com.electron.services;

import com.electron.domain.Dispositivo;
import com.electron.repositories.DispositivoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    private final DispositivoRepository dispositivoRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public List<Dispositivo> listarTodos() {
        return dispositivoRepository.findAll();
    }

    public Dispositivo listarPorId(Long id) {
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo não encontrado!"));
    }

    public Dispositivo criar(Dispositivo dispositivo) {
        try {
            return dispositivoRepository.save(dispositivo);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao criar dispositivo. Verifique os dados.", e);
        }
    }

    public Dispositivo atualizar(Long id, Dispositivo dispositivo) {
        Dispositivo dispositivoExistente = dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo não encontrado!"));

        // Atualiza os campos necessários

//  TODO arrumar isso      dispositivoExistente.set(dispositivo.getDispositivoFkEmpresa());
        dispositivoExistente.setDispositivoTipo(dispositivo.getDispositivoTipo());
        dispositivoExistente.setDispositivoMarca(dispositivo.getDispositivoMarca());
        dispositivoExistente.setDispositivoModelo(dispositivo.getDispositivoModelo());
        dispositivoExistente.setDispositivoNumeroSerie(dispositivo.getDispositivoNumeroSerie());
        dispositivoExistente.setDispositivoAtivo(dispositivo.getDispositivoAtivo());
        dispositivoExistente.setDispositivoDataInstalacao(dispositivo.getDispositivoDataInstalacao());
        dispositivoExistente.setDispositivoFkUsuario(dispositivo.getDispositivoFkUsuario());
        dispositivoExistente.setDispositivoObservacoes(dispositivo.getDispositivoObservacoes());

        return dispositivoRepository.save(dispositivoExistente);
    }

    public void deletar(Long id) {
        if (dispositivoRepository.findById(id).isPresent()) {
            dispositivoRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("Não foi possível deletar o dispositivo de id " + id);
    }
}
