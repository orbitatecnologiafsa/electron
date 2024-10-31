package com.electron.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CodigoAjuste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_ajuste_id")
    private Long id;

    @Column(name = "codigo_ajuste_codigo", length = 100)
    private String codigo;

    @Column(name = "codigo_ajuste_descricao", columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "codigo_ajuste_data_inicio")
    private String dataInicio;

    @Column(name = "codigo_ajuste_data_fim")
    private String dataFim;
}
