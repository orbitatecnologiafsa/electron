package com.electron.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pdv_empresa")
@Data
@NoArgsConstructor
public class PdvEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pdv_nome", nullable = false, length = 100)
    private String pdvNome;

    @Column(name = "pdv_numero_caixa", nullable = false, length = 10)
    private String pdvNumeroCaixa;

    @Column(name = "pdv_ativo")
    private Boolean pdvAtivo = true;

    @Column(name = "pdv_data_instalacao")
    private LocalDate pdvDataInstalacao;

    @Lob
    @Column(name = "pdv_observacoes")
    private String pdvObservacoes;

    @ManyToOne
    @JoinColumn(name = "pdv_fk_empresa", insertable = false, updatable = false)
    private EmpresaProprietaria empresa;

    @ManyToOne
    @JoinColumn(name = "pdv_fk_dispositivo", insertable = false, updatable = false)
    private Dispositivo dispositivo;

}
