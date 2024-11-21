package com.electron.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.electron.domain.OutrasInformacoes;
import com.electron.repositories.OutrasInformacoesRepository;
import com.electron.services.exceptions.NotFoundException;

@Service
public class OutrasInformacoesService {
    private final OutrasInformacoesRepository outrasInformacoesRepository;

    public OutrasInformacoesService(OutrasInformacoesRepository outrasInformacoesRepository) {
        this.outrasInformacoesRepository = outrasInformacoesRepository;
    }

    public Page<OutrasInformacoes> listarTodos(Pageable pageable) {
        return outrasInformacoesRepository.findAll(pageable);
    }

    public OutrasInformacoes listarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        return outrasInformacoesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Outras informações não encontradas com ID: " + id));
    }

    public OutrasInformacoes criar(OutrasInformacoes outrasInformacoes) {
        if (outrasInformacoes == null) {
            throw new IllegalArgumentException("Outras informações não podem ser nulas");
        }
        return outrasInformacoesRepository.save(outrasInformacoes);
    }

    public OutrasInformacoes atualizar(Long id, OutrasInformacoes outrasInformacoes) {
        if (id == null || outrasInformacoes == null) {
            throw new IllegalArgumentException("ID e outras informações não podem ser nulos");
        }

        OutrasInformacoes existente = listarPorId(id);
        
        existente.setPessoa(outrasInformacoes.getPessoa());
        existente.setEstadoCivil(outrasInformacoes.getEstadoCivil());
        existente.setConjuge(outrasInformacoes.getConjuge());
        existente.setNomeMae(outrasInformacoes.getNomeMae());
        existente.setNomePai(outrasInformacoes.getNomePai());
        existente.setLocalTrabalho(outrasInformacoes.getLocalTrabalho());
        existente.setOutrasProfissao(outrasInformacoes.getOutrasProfissao());
        existente.setDataNascimento(outrasInformacoes.getDataNascimento());
        existente.setNaturalidade(outrasInformacoes.getNaturalidade());
        existente.setDiaAcerto(outrasInformacoes.getDiaAcerto());
        existente.setRendaMensal(outrasInformacoes.getRendaMensal());
        existente.setLimiteCredito(outrasInformacoes.getLimiteCredito());
        existente.setCreditoDisponivel(outrasInformacoes.getCreditoDisponivel());
        existente.setCreditoData(outrasInformacoes.getCreditoData());
        existente.setDiaFaturamento(outrasInformacoes.getDiaFaturamento());
        existente.setFormaPagamento(outrasInformacoes.getFormaPagamento());
        existente.setNumParcelasFaturamento(outrasInformacoes.getNumParcelasFaturamento());
        existente.setReterIr(outrasInformacoes.getReterIr());
        existente.setReterCsll(outrasInformacoes.getReterCsll());
        existente.setReterPrevidenciaSocial(outrasInformacoes.getReterPrevidenciaSocial());
        existente.setReterCofins(outrasInformacoes.getReterCofins());
        existente.setReterPis(outrasInformacoes.getReterPis());

        return outrasInformacoesRepository.save(existente);
    }

    public void deletar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }
        if (!outrasInformacoesRepository.existsById(id)) {
            throw new NotFoundException("Outras informações não encontradas com ID: " + id);
        }
        outrasInformacoesRepository.deleteById(id);
    }
}
