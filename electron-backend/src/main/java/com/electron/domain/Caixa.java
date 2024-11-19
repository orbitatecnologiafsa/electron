package com.electron.domain;

import java.time.LocalDate;

import com.electron.domain.enums.TipoCaixa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "caixas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "caixa_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "caixa_tipo", nullable = false)
    private TipoCaixa tipo;

    @Column(name = "caixa_numero", nullable = false)
    private Integer numero;

    @Column(name = "caixa_modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "caixa_serie", nullable = false, unique = true, length = 50)
    @org.hibernate.annotations.Index(name = "idx_caixa_serie")
    private String serie;

    @Column(name = "caixa_ativo")
    private Boolean ativo = true;

    @Column(name = "caixa_data_instalacao")
    private LocalDate dataInstalacao;

    @Column(name = "caixa_observacoes")
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caixa_fk_empresa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private EmpresaProprietaria empresaProprietaria;

}
