package com.electron.domain.dtos;

import com.electron.domain.Pessoa;
import com.electron.domain.Vendedor;
import com.electron.domain.enums.BaseCalculo;
import com.electron.domain.enums.TipoComissao;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {
    private Pessoa pessoa;

    private BigDecimal desconto;

    private BigDecimal comissao;

    private TipoComissao tipoComissao;

    private BaseCalculo baseCalculo;

    private String observacoes;

    public Vendedor toVendedor() {
        Vendedor v = new Vendedor(null, pessoa, desconto, comissao, tipoComissao, baseCalculo, observacoes);
        return v;
    }


}