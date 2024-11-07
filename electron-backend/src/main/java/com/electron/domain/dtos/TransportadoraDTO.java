package com.electron.domain.dtos;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Pessoa;
import com.electron.domain.Transportadora;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransportadoraDTO {
    private Pessoa pessoa;

    private EmpresaProprietaria empresa;

    private String placaVeiculo;

    private String anttVeiculo;

    public Transportadora toTransportadora(){
        Transportadora t = new Transportadora(null, pessoa, empresa, placaVeiculo, anttVeiculo);
        return t;
    }

}
