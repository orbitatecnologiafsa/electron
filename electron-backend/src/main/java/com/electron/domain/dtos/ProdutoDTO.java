package com.electron.domain.dtos;

import com.electron.domain.enums.ClassificacaoProduto;
import com.electron.domain.enums.TipoControle;
import com.electron.domain.enums.Unidade;
import com.electron.domain.enums.UnidadeEmbalagem;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "Código é obrigatório")
    @Size(max = 50, message = "Código não pode ter mais que 50 caracteres")
    private String codigo;

    @Size(max = 50, message = "Código de barras não pode ter mais que 50 caracteres")
    private String barras;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome não pode ter mais que 100 caracteres")
    private String nome;

    @Size(max = 1000, message = "Descrição não pode ter mais que 1000 caracteres")
    private String descricao;

    @NotNull(message = "Unidade de entrada é obrigatória")
    private Unidade unidadeEntrada;

    @NotNull(message = "Unidade de saída é obrigatória")
    private Unidade unidadeSaida;

    private Unidade unidadeEstocagem;
    private Unidade unidadeTributacao;

    private Long fatorConversaoId;

    @NotNull(message = "Grupo é obrigatório")
    private Long grupoProdServId;

    private ClassificacaoProduto classificacao;

    @Min(value = 0, message = "Dias de validade não pode ser negativo")
    private Integer diasValidade;

    private TipoControle tipoControle;

    @DecimalMin(value = "0.0", message = "Preço de custo não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Preço de custo deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal precoCusto;

    @DecimalMin(value = "0.0", message = "Preço de custo médio não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Preço de custo médio deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal precoCustoMedio;

    @DecimalMin(value = "0.0", message = "Margem de lucro não pode ser negativa")
    @Digits(integer = 10, fraction = 2, message = "Margem de lucro deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal precoMargemLucro;

    @NotNull(message = "Preço de venda é obrigatório")
    @DecimalMin(value = "0.0", message = "Preço de venda não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Preço de venda deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal precoVenda;

    @DecimalMin(value = "0.0", message = "Preço de revenda não pode ser negativo")
    @Digits(integer = 10, fraction = 2, message = "Preço de revenda deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal precoRevenda;

    @DecimalMin(value = "0.0", message = "Saldo não pode ser negativo")
    private BigDecimal saldo;

    @DecimalMin(value = "0.0", message = "Quantidade bloqueada não pode ser negativa")
    private BigDecimal bloqueado;

    @DecimalMin(value = "0.0", message = "Pedido de venda não pode ser negativo")
    private BigDecimal pedidoVenda;

    @DecimalMin(value = "0.0", message = "Quantidade disponível não pode ser negativa")
    private BigDecimal disponivel;

    @DecimalMin(value = "0.0", message = "Saldo ideal não pode ser negativo")
    private BigDecimal saldoIdeal;

    @Size(max = 50, message = "Código de ajuste não pode ter mais que 50 caracteres")
    private String codigoAjuste;

    private Long codigoCestNcmId;
    private Long tributacaoEstadualId;
    private Long tributacaoFederalId;

    @Size(max = 1000, message = "Observações não podem ter mais que 1000 caracteres")
    private String observacoes;

    @Size(max = 100, message = "Informação extra balança não pode ter mais que 100 caracteres")
    private String informacaoExtraBalanca;

    private UnidadeEmbalagem unidadeEmbalagem;

    @DecimalMin(value = "0.0", message = "Quantidade da embalagem não pode ser negativa")
    private BigDecimal quantidadeEmbalagem;

    private Long codigoAnpId;

    @Size(max = 50, message = "Referência não pode ter mais que 50 caracteres")
    private String referencia;

    @Size(max = 100, message = "Localização não pode ter mais que 100 caracteres")
    private String localizacao;
}
