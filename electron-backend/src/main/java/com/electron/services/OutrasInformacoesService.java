package com.electron.services;

import com.electron.domain.OutrasInformacoes;
import com.electron.repositories.OutrasInformacoesRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutrasInformacoesService {
    private OutrasInformacoesRepository outrasInformacoesRepository;

    public OutrasInformacoesService(OutrasInformacoesRepository outrasInformacoesRepository) {
        this.outrasInformacoesRepository = outrasInformacoesRepository;
    }

    public List<OutrasInformacoes> listarTodos() {
        return outrasInformacoesRepository.findAll();
    }

    public OutrasInformacoes listarPorId(Long id) {
        return outrasInformacoesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Outras informações não encontradas"));
    }

    public void criar(OutrasInformacoes outrasInformacoes) {
        outrasInformacoesRepository.save(outrasInformacoes);
    }

    public void atualizar(Long id, OutrasInformacoes outrasInformacoes) {
        OutrasInformacoes outrasInformacoesObj = listarPorId(id);

        outrasInformacoesObj.setPessoa(outrasInformacoes.getPessoa());
        outrasInformacoesObj.setEstadoCivil(outrasInformacoes.getEstadoCivil());
        outrasInformacoesObj.setOutrasConjuge(outrasInformacoes.getOutrasConjuge());
        outrasInformacoesObj.setOutrasNomeMae(outrasInformacoes.getOutrasNomeMae());
        outrasInformacoesObj.setOutrasNomePai(outrasInformacoes.getOutrasNomePai());
        outrasInformacoesObj.setOutrasLocalTrabalho(outrasInformacoes.getOutrasLocalTrabalho());
        outrasInformacoesObj.setOutrasProfissao(outrasInformacoes.getOutrasProfissao());
        outrasInformacoesObj.setOutrasDataNascimento(outrasInformacoes.getOutrasDataNascimento());
        outrasInformacoesObj.setOutrasNaturalidade(outrasInformacoes.getOutrasNaturalidade());
        outrasInformacoesObj.setOutrasDiaAcerto(outrasInformacoes.getOutrasDiaAcerto());
        outrasInformacoesObj.setOutrasRendaMensal(outrasInformacoes.getOutrasRendaMensal());
        outrasInformacoesObj.setOutrasLimiteCredito(outrasInformacoes.getOutrasLimiteCredito());
        outrasInformacoesObj.setOutrasCreditoDisponivel(outrasInformacoes.getOutrasCreditoDisponivel());
        outrasInformacoesObj.setOutrasCreditoData(outrasInformacoes.getOutrasCreditoData());
        outrasInformacoesObj.setOutrasDiaFaturamento(outrasInformacoes.getOutrasDiaFaturamento());
        outrasInformacoesObj.setFormaPagamento(outrasInformacoes.getFormaPagamento());
        outrasInformacoesObj.setOutrasNumParcelasFaturamento(outrasInformacoes.getOutrasNumParcelasFaturamento());
        outrasInformacoesObj.setReterIr(outrasInformacoes.getReterIr());
        outrasInformacoesObj.setReterCsll(outrasInformacoes.getReterCsll());
        outrasInformacoesObj.setReterPrevidenciaSocial(outrasInformacoes.getReterPrevidenciaSocial());
        outrasInformacoesObj.setReterCofins(outrasInformacoes.getReterCofins());
        outrasInformacoesObj.setReterPis(outrasInformacoes.getReterPis());

        outrasInformacoesRepository.save(outrasInformacoesObj);
    }

    public void deletar(Long id) {
        outrasInformacoesRepository.deleteById(id);
    }
}
