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
    @Column(name = "outras_informacoes_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "outras_fk_pessoas", nullable = false)
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    @Column(name = "outras_estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name = "outras_conjuge")
    private String conjuge;

    @Column(name = "outras_nome_mae")
    private String nomeMae;

    @Column(name = "outras_nome_pai")
    private String nomePai;

    @Column(name = "outras_local_trabalho")
    private String localTrabalho;

    @Column(name = "outras_profissao", length = 100)
    private String outrasProfissao;

    @Column(name = "outras_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "outras_naturalidade")
    private String naturalidade;

    @Column(name = "outras_dia_acerto", length = 11)
    private Integer diaAcerto;

    @Column(name = "outras_renda_mensal", precision = 10, scale = 2)
    private BigDecimal rendaMensal;

    @Column(name = "outras_limite_credito", precision = 10, scale = 2)
    private BigDecimal limiteCredito;

    @Column(name = "outras_credito_disponivel", precision = 10, scale = 2)
    private BigDecimal creditoDisponivel;

    @Column(name = "outras_credito_data")
    private LocalDate creditoData;

    @Column(name = "outras_dia_faturamento", length = 11)
    private Integer diaFaturamento;

    @Column(name = "outras_formadepagamento", length = 100)
    private String formaPagamento;

    @Column(name = "outras_num_parcelas_faturamento", length = 11)
    private Integer numParcelasFaturamento;

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
