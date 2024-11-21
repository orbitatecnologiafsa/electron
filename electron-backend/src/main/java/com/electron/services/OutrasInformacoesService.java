package com.electron.services;

import com.electron.domain.OutrasInformacoes;
import com.electron.repositories.OutrasInformacoesRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return outrasInformacoesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Outras informações não encontradas"));
    }

    public OutrasInformacoes criar(OutrasInformacoes outrasInformacoes) {
        return outrasInformacoesRepository.save(outrasInformacoes);
    }

    public OutrasInformacoes atualizar(Long id, OutrasInformacoes outrasInformacoes) {
        OutrasInformacoes outrasInformacoesObj = listarPorId(id);
        outrasInformacoesObj.setPessoa(outrasInformacoes.getPessoa());
        outrasInformacoesObj.setEstadoCivil(outrasInformacoes.getEstadoCivil());
        outrasInformacoesObj.setConjuge(outrasInformacoes.getConjuge());
        outrasInformacoesObj.setNomeMae(outrasInformacoes.getNomeMae());
        outrasInformacoesObj.setNomePai(outrasInformacoes.getNomePai());
        outrasInformacoesObj.setLocalTrabalho(outrasInformacoes.getLocalTrabalho());
        outrasInformacoesObj.setOutrasProfissao(outrasInformacoes.getOutrasProfissao());
        outrasInformacoesObj.setDataNascimento(outrasInformacoes.getDataNascimento());
        outrasInformacoesObj.setNaturalidade(outrasInformacoes.getNaturalidade());
        outrasInformacoesObj.setDiaAcerto(outrasInformacoes.getDiaAcerto());
        outrasInformacoesObj.setRendaMensal(outrasInformacoes.getRendaMensal());
        outrasInformacoesObj.setLimiteCredito(outrasInformacoes.getLimiteCredito());
        outrasInformacoesObj.setCreditoDisponivel(outrasInformacoes.getCreditoDisponivel());
        outrasInformacoesObj.setCreditoData(outrasInformacoes.getCreditoData());
        outrasInformacoesObj.setDiaFaturamento(outrasInformacoes.getDiaFaturamento());
        outrasInformacoesObj.setFormaPagamento(outrasInformacoes.getFormaPagamento());
        outrasInformacoesObj.setNumParcelasFaturamento(outrasInformacoes.getNumParcelasFaturamento());
        outrasInformacoesObj.setReterIr(outrasInformacoes.getReterIr());
        outrasInformacoesObj.setReterCsll(outrasInformacoes.getReterCsll());
        outrasInformacoesObj.setReterPrevidenciaSocial(outrasInformacoes.getReterPrevidenciaSocial());
        outrasInformacoesObj.setReterCofins(outrasInformacoes.getReterCofins());
        outrasInformacoesObj.setReterPis(outrasInformacoes.getReterPis());

        return outrasInformacoesRepository.save(outrasInformacoesObj);
    }

    public void deletar(Long id) {
        outrasInformacoesRepository.deleteById(id);
    }
}
