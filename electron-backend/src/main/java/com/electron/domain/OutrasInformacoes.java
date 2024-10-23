package com.electron.domain;

import com.electron.domain.enums.EstadoCivil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "outras_informacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutrasInformacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long outrasInformacoesId;

    @ManyToOne
    @JoinColumn(name = "outras_fk_pessoas", nullable = false)
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "outras_estado_civil", length = 20)
    private EstadoCivil estadoCivil;

    @Column(name = "outras_conjuge", length = 255)
    private String outrasConjuge;

    @Column(name = "outras_nome_mae", length = 255)
    private String outrasNomeMae;

    @Column(name = "outras_nome_pai", length = 255)
    private String outrasNomePai;

    @Column(name = "outras_local_trabalho", length = 255)
    private String outrasLocalTrabalho;

    @Column(name = "outras_profissao", length = 100)
    private String outrasProfissao;

    @Column(name = "outras_data_nascimento")
    private LocalDate outrasDataNascimento;

    @Column(name = "outras_naturalidade", length = 255)
    private String outrasNaturalidade;

    @Column(name = "outras_dia_acerto")
    private Integer outrasDiaAcerto;

    @Column(name = "outras_renda_mensal")
    private BigDecimal outrasRendaMensal;

    @Column(name = "outras_limite_credito")
    private BigDecimal outrasLimiteCredito;

    @Column(name = "outras_credito_disponivel")
    private BigDecimal outrasCreditoDisponivel;

    @Column(name = "outras_credito_data")
    private LocalDate outrasCreditoData;

    @Column(name = "outras_dia_faturamento")
    private Integer outrasDiaFaturamento;

    @ManyToOne
    @JoinColumn(name = "outras_fk_formadepagamento")
    private FormaPagamento formaPagamento;

    @Column(name = "outras_num_parcelas_faturamento")
    private Integer outrasNumParcelasFaturamento;

    @Column(name = "reter_ir")
    private Boolean reterIr = false;

    @Column(name = "reter_csll")
    private Boolean reterCsll = false;

    @Column(name = "reter_previdencia_social")
    private Boolean reterPrevidenciaSocial = false;

    @Column(name = "reter_cofins")
    private Boolean reterCofins = false;

    @Column(name = "reter_pis")
    private Boolean reterPis = false;
}
