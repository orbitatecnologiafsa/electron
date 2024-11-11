package com.electron.domain.dtos;

import com.electron.domain.Municipio;
import com.electron.domain.Transportadora;
import com.electron.domain.enums.TipoPessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportadoraDTO {

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
    private String observacoes;
    private String placaVeiculo;
    private String anttVeiculo;

    public Transportadora toTransportadora() {
        return new Transportadora(
                tipo, foto, entidade, cpfCnpj, nomeRazaoSocial, nomeFantasia, passaporte,
                rgInscricaoEstadual, inscricaoMunicipal, contato, cep, logradouro, numero,
                bairro, complemento, municipio, telefone, celular, email, dataDeNascimento,
                observacoes, null, placaVeiculo, anttVeiculo
        );
    }
}
