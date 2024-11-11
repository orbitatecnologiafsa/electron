package com.electron.domain.dtos;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Municipio;
import com.electron.domain.Vendedor;
import com.electron.domain.enums.BaseCalculo;
import com.electron.domain.enums.TipoComissao;
import com.electron.domain.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {

    // Campos de BasePessoa
    private TipoPessoa tipo;
    private String foto;
    private String entidade;
    private String cpfCnpj;
    private String nomeRazaoSocial;
    private String nomeFantasia;
    private String passaporte;
    private String rgInscricaoEstadual;
    private String inscricaoMunicipal;
    private String contato;
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private Municipio municipio;
    private String telefone;
    private String celular;
    private String email;
    private LocalDate dataDeNascimento;
    private String observacoesPessoa;
    private EmpresaProprietaria empresa;

    // Campos específicos de Vendedor
    private BigDecimal desconto;
    private BigDecimal comissao;
    private TipoComissao tipoComissao;
    private BaseCalculo baseCalculo;
    private String observacoes;

    public Vendedor toVendedor() {
        return new Vendedor(
            tipo,
            foto,
            entidade,
            cpfCnpj,
            nomeRazaoSocial,
            nomeFantasia,
            passaporte,
            rgInscricaoEstadual,
            inscricaoMunicipal,
            contato,
            cep,
            logradouro,
            numero,
            bairro,
            complemento,
            municipio,
            telefone,
            celular,
            email,
            dataDeNascimento,
            observacoesPessoa,
            empresa,
            desconto,
            comissao,
            tipoComissao,
            baseCalculo,
            observacoes
        );
    }
}
