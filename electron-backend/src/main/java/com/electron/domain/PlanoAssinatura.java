package com.electron.domain;

import java.time.LocalDate;

import com.electron.domain.enums.TipoAssinatura;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "plano_assinatura")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoAssinatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assinatura_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "assinatura_tipo", nullable = false)
    private TipoAssinatura tipo;

    @Column(name = "assinatura_data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "assinatura_data_fim", nullable = false)
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "assinatura_fk_empresa")
    private EmpresaProprietaria empresa;

}
