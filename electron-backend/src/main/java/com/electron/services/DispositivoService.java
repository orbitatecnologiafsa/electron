package com.electron.services;

import com.electron.domain.Dispositivo;
import com.electron.repositories.DispositivoRepository;
import com.electron.services.exceptions.AlreadyExistException;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {
    private final DispositivoRepository dispositivoRepository;

    public DispositivoService(DispositivoRepository dispositivoRepository) {
        this.dispositivoRepository = dispositivoRepository;
    }

    public Page<Dispositivo> listarTodos(Pageable pageable) {
        return dispositivoRepository.findAll(pageable);
    }

    public Dispositivo listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return dispositivoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dispositivo não encontrado com ID: " + id));
    }

    public Dispositivo criar(Dispositivo dispositivo) {
        if (dispositivo == null) {
            throw new IllegalArgumentException("Dispositivo não pode ser nulo");
        }
        if (dispositivo.getDispositivoNumeroSerie() == null || dispositivo.getDispositivoNumeroSerie().trim().isEmpty()) {
            throw new IllegalArgumentException("Número de série é obrigatório");
        }
        if (dispositivoRepository.findByDispositivoNumeroSerie(dispositivo.getDispositivoNumeroSerie()).isPresent()) {
            throw new AlreadyExistException("Já existe um dispositivo com este número de série: " + dispositivo.getDispositivoNumeroSerie());
        }
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo atualizar(Long id, Dispositivo dispositivo) {
        if (id == null || dispositivo == null) {
            throw new IllegalArgumentException("ID e dispositivo não podem ser nulos");
        }

        Dispositivo existente = listarPorId(id);
        
        if (!existente.getDispositivoNumeroSerie().equals(dispositivo.getDispositivoNumeroSerie()) &&
            dispositivoRepository.findByDispositivoNumeroSerie(dispositivo.getDispositivoNumeroSerie()).isPresent()) {
            throw new AlreadyExistException("Já existe um dispositivo com este número de série: " + dispositivo.getDispositivoNumeroSerie());
        }

        existente.setDispositivoTipo(dispositivo.getDispositivoTipo());
        existente.setDispositivoMarca(dispositivo.getDispositivoMarca());
        existente.setDispositivoModelo(dispositivo.getDispositivoModelo());
        existente.setDispositivoNumeroSerie(dispositivo.getDispositivoNumeroSerie());
        existente.setDispositivoAtivo(dispositivo.getDispositivoAtivo());
        existente.setDispositivoDataInstalacao(dispositivo.getDispositivoDataInstalacao());
        existente.setDispositivoFkUsuario(dispositivo.getDispositivoFkUsuario());
        existente.setDispositivoObservacoes(dispositivo.getDispositivoObservacoes());

        return dispositivoRepository.save(existente);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!dispositivoRepository.existsById(id)) {
            throw new NotFoundException("Dispositivo não encontrado com ID: " + id);
        }
        dispositivoRepository.deleteById(id);
    }
}
