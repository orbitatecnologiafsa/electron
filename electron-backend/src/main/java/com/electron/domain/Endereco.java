package com.electron.domain;

import com.electron.domain.enums.TipoEndereco;
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
@Table(name = "enderecos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "endereco_tipo", nullable = false)
    private TipoEndereco tipoEndereco;

    @Column(name = "endereco_cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "endereco_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "endereco_bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_telefone", length = 15)
    private String telefone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_fk_pessoas")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "enderecos"})
    @ToString.Exclude
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_fk_municipios")
    private Municipio municipio;

}
