package com.electron.domain.dtos;

import com.electron.domain.Caixa;
import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.enums.TipoCaixa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaDTO {
     private TipoCaixa tipo;

    private Integer numero;

    private String modelo;

    private String serie;

    private Boolean ativo = true;

    private LocalDate dataInstalacao;

    private String observacoes;

    private EmpresaProprietaria empresaProprietaria;

    public Caixa toCaixa() {
        return new Caixa(
            null, tipo, numero, modelo, serie, ativo, dataInstalacao, observacoes, empresaProprietaria
        );
    }
}
