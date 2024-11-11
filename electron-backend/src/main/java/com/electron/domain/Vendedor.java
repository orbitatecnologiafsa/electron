package com.electron.domain;

import com.electron.domain.enums.BaseCalculo;
import com.electron.domain.enums.TipoComissao;
import com.electron.domain.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendedores")
public class Vendedor extends BasePessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedores_id")
    private Long id;

    @Column(name = "vendedor_desconto", precision = 10, scale = 2)
    private BigDecimal desconto;

    @Column(name = "vendedor_comissao", precision = 10, scale = 2)
    private BigDecimal comissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendedor_tipo_comissao")
    private TipoComissao tipoComissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendedor_base_calculo")
    private BaseCalculo baseCalculo;

    @Column(name = "vendedor_observacoes", columnDefinition = "TEXT")
    private String observacoes;

    public Vendedor(TipoPessoa tipo, String foto, String entidade, String cpfCnpj,
                    String nomeRazaoSocial, String nomeFantasia, String passaporte,
                    String rgInscricaoEstadual, String inscricaoMunicipal, String contato,
                    String cep, String logradouro, String numero, String bairro,
                    String complemento, Municipio municipio, String telefone,
                    String celular, String email, LocalDate dataDeNascimento,
                    String observacoesPessoa, EmpresaProprietaria empresa,
                    BigDecimal desconto, BigDecimal comissao, TipoComissao tipoComissao,
                    BaseCalculo baseCalculo, String observacoes) {
        super(null, tipo, foto, entidade, cpfCnpj, nomeRazaoSocial, nomeFantasia, passaporte,
                rgInscricaoEstadual, inscricaoMunicipal, contato, cep, logradouro, numero,
                bairro, complemento, municipio, telefone, celular, email, dataDeNascimento,
                observacoesPessoa, empresa);
        this.desconto = desconto;
        this.comissao = comissao;
        this.tipoComissao = tipoComissao;
        this.baseCalculo = baseCalculo;
        this.observacoes = observacoes;
    }
}
