package com.electron.domain;

import com.electron.domain.enums.TipoEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enderecos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enderecoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEndereco tipoEndereco;

    @Column(name = "endereco_fk_pessoas", nullable = false)
    private Long enderecoFkPessoas;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    private String complemento;

    private String telefone;

    @Column(name = "endereco_fk_municipios")
    private Long enderecoFkMunicipios;

    @ManyToOne
    @JoinColumn(name = "endereco_fk_pessoas", insertable = false, updatable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_fk_municipios", insertable = false, updatable = false)
    private Municipio municipio;

}
