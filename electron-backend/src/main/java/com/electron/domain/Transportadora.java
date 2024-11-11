package com.electron.domain;

import com.electron.domain.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Column(name = "placa_veiculo", length = 7)
    private String placaVeiculo;

    @Column(name = "antt_veiculo", length = 20)
    private String anttVeiculo;

    public Transportadora(TipoPessoa tipo, String foto, String entidade, String cpfCnpj,
                          String nomeRazaoSocial, String nomeFantasia, String passaporte,
                          String rgInscricaoEstadual, String inscricaoMunicipal, String contato,
                          String cep, String logradouro, String numero, String bairro,
                          String complemento, Municipio municipio, String telefone,
                          String celular, String email, LocalDate dataDeNascimento,
                          String observacoes, EmpresaProprietaria empresa,
                          String placaVeiculo, String anttVeiculo) {
        super(null, tipo, foto, entidade, cpfCnpj, nomeRazaoSocial, nomeFantasia, passaporte,
                rgInscricaoEstadual, inscricaoMunicipal, contato, cep, logradouro, numero,
                bairro, complemento, municipio, telefone, celular, email, dataDeNascimento,
                observacoes, empresa);
        this.placaVeiculo = placaVeiculo;
        this.anttVeiculo = anttVeiculo;
    }

}
