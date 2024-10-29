package com.electron.domain;

import com.electron.domain.enums.TipoCaixa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String serie;

    @Column(name = "caixa_ativo")
    private Boolean ativo = true;

    @Column(name = "caixa_data_instalacao")
    private LocalDate dataInstalacao;

    @Column(name = "caixa_observacoes")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "caixa_fk_empresa", insertable = false, updatable = false)
    private EmpresaProprietaria empresaProprietaria;

}
