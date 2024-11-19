package com.electron.domain;

import com.electron.domain.enums.PagamentoAceito;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "pdv_empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdvEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pdv_id")
    private Long id;

    @Column(name = "pdv_nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "pdv_numero_caixa", nullable = false, length = 10)
    private String numeroCaixa;

    @ManyToOne
    @JoinColumn(name = "pdv_fk_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "pdv_tipo_pagamento_aceito", nullable = false)
    private PagamentoAceito pagamentoAceito;

    @Column(name = "pdv_ativo")
    private Boolean ativo = true;

    @Column(name = "pdv_data_instalacao")
    private LocalDate dataInstalacao;

    @Column(name = "pdv_data_ultimo_acesso")
    private LocalDate dataUltimoAcesso;

    @Column(name = "pdv_mode_teste")
    private Boolean modoTeste = false;

    @Column(name = "pdv_chave_acesso", unique = true)
    private String chaveAcesso;

    @Column(name = "pdv_fiscal")
    private Boolean fiscal = false;

    @Column(name = "pdv_cfop_padrao", length = 11)
    private Integer cfopPadrao;

    @Column(name = "pdv_observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
