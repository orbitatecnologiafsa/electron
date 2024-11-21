package com.electron.domain.dtos;

import com.electron.domain.Municipio;
import com.electron.domain.Pessoa;
import com.electron.domain.enums.TipoEndereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private TipoEndereco tipoEndereco;

    private String cep;

    private String logradouro;

    private String numero;

    private String bairro;

    private String complemento;

    private String telefone;

    private Pessoa pessoa;

    private Municipio municipio;

  }
