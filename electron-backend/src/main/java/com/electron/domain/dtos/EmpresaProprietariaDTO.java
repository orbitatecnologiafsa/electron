package com.electron.domain.dtos;

import com.electron.domain.Caixa;
import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Municipio;
import com.electron.domain.enums.RegimeTributario;
import com.electron.domain.enums.TipoPessoa;
import com.electron.domain.enums.TipoUnidade;
import com.electron.domain.enums.VersaoEmpresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaProprietariaDTO {
    private TipoUnidade tipoUnidade;

    private TipoPessoa tipoPessoa;

    private String cpfCnpj;

    private VersaoEmpresa versao;

    private RegimeTributario regimeTributario;

    private String cnae;

    private String razaoSocial;

    private String numeroFilial;

    private String digitoVerificador;

    private Boolean ativo = true;

    private String nomeFantasia;

    private String nomeExibicao;

    private String naturezaJuridica;

    private String inscricaoEstadual;

    private String inscricaoMunicipal;

    private String contato;

    private String telefone;

    private String email;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String complemento;

    private Municipio municipio;

    private String chaveAcesso;

    private LocalDate dataCriacao;

    private String descricaoAtividades;

    private String observacoes;

    private List<Caixa> caixas;

}
