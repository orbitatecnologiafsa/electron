package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "formasdepagamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formaPagamentoId;

    @Column(name = "forma_pagamento_nome", length = 100, nullable = false)
    private String formaPagamentoNome;

    @Column(name = "forma_pagamento_descricao", columnDefinition = "TEXT")
    private String formaPagamentoDescricao;

    @Column(name = "data_inicio_vigencia", nullable = false)
    private LocalDate dataInicioVigencia;

    @Column(name = "data_fim_vigencia")
    private LocalDate dataFimVigencia;

    @Column(name = "forma_pagamento_observacoes", columnDefinition = "TEXT")
    private String formaPagamentoObservacoes;
}
