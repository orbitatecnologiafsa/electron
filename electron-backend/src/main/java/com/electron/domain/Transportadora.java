package com.electron.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "transportadora")
public class Transportadora extends BasePessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportadoraId;

    @ManyToOne
    @JoinColumn(name = "transportadora_fk_pessoas")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "transportadora_fk_empresa")
    private EmpresaProprietaria empresa;

    @Column(name = "placa_veiculo", length = 7)
    private String placaVeiculo;

    @Column(name = "antt_veiculo", length = 20)
    private String anttVeiculo;
}
