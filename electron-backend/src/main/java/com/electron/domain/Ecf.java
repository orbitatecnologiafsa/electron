package com.electron.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ecfs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ecf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ecf_fk_empresa", nullable = false)
    private Long ecfFkEmpresa;

    @Column(name = "ecf_fk_usuario")
    private Long ecfFkUsuario;

    @Column(name = "ecf_marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "ecf_modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "ecf_numero_serie", nullable = false, unique = true, length = 50)
    private String numeroSerie;

    @Column(name = "ecf_ativo")
    private Boolean ativo = true;

    @Column(name = "ecf_shipay")
    private Boolean shipay = false;

    // Relacionamentos
    @ManyToOne
    @JoinColumn(name = "ecf_fk_empresa", insertable = false, updatable = false)
    private EmpresaProprietaria empresa;

    @ManyToOne
    @JoinColumn(name = "ecf_fk_usuario", insertable = false, updatable = false)
    private EcfUsuario usuario;
}
