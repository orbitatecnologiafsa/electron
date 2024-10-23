package com.electron.domain;

import com.electron.domain.enums.BaseCalculo;
import com.electron.domain.enums.TipoComissao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vendedores")
public class Vendedores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendedores_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vendedor_fk_pessoas", referencedColumnName = "pessoas_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "vendedor_desconto", precision = 10, scale = 2)
    private Double desconto;

    @Column(name = "vendedor_comissao", precision = 10, scale = 2)
    private Double comissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendedor_tipo_comissao")
    private TipoComissao tipoComissao;

    @Enumerated(EnumType.STRING)
    @Column(name = "vendedor_base_calculo")
    private BaseCalculo baseCalculo;

    @ManyToOne
    @JoinColumn(name = "vendedor_fk_empresa", referencedColumnName = "empresa_id", nullable = false)
    private EmpresaProprietaria empresa;

    @Column(name = "vendedor_observacoes", columnDefinition = "TEXT")
    private String observacoes;

}
