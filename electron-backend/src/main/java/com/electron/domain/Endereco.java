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
    @Column(name = "endereco_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "endereco_tipo", nullable = false)
    private TipoEndereco tipoEndereco;

    @Column(name = "endereco_cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "endereco_logradoudo", nullable = false)
    private String logradouro;

    @Column(name = "endereco_numero", nullable = false, length = 10)
    private String numero;

    @Column(name = "endereco_bairro", nullable = false, length = 100)
    private String bairro;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_telefone", length = 15)
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "endereco_fk_pessoas", insertable = false, updatable = false, nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_fk_municipios", insertable = false, updatable = false)
    private Municipio municipio;

}
