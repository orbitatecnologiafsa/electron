package com.electron.domain.dtos;

import com.electron.domain.OutrasInformacoes;
import com.electron.domain.Pessoa;
import com.electron.domain.enums.EstadoCivil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutrasInformacoesDTO {
    private Pessoa pessoa;

    private EstadoCivil estadoCivil;

    private String conjuge;

    private String nomeMae;

    private String nomePai;

    private String localTrabalho;

    private String outrasProfissao;

    private LocalDate dataNascimento;

    private String naturalidade;

    private Integer diaAcerto;

    private BigDecimal rendaMensal;

    private BigDecimal limiteCredito;

    private BigDecimal creditoDisponivel;

    private LocalDate creditoData;

    private Integer diaFaturamento;

    private String formaPagamento;

    private Integer numParcelasFaturamento;

    private Boolean reterIr = false;

    private Boolean reterCsll = false;

    private Boolean reterPrevidenciaSocial = false;

    private Boolean reterCofins = false;

    private Boolean reterPis = false;

    public OutrasInformacoes toOutrasInformacoes() {
        return new OutrasInformacoes(
                null, pessoa, estadoCivil, conjuge, nomeMae, nomePai, localTrabalho, outrasProfissao, dataNascimento,
                naturalidade, diaAcerto, rendaMensal, limiteCredito, creditoDisponivel, creditoData, diaFaturamento,
                formaPagamento, numParcelasFaturamento, reterIr, reterCsll, reterPrevidenciaSocial, reterCofins, reterPis
        );
    }
}
