package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transportadora")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transportadora extends BasePessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transportadora_id")
    private Long id;

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
