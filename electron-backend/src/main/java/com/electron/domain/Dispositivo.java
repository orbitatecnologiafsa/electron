package com.electron.domain;

import java.time.LocalDateTime;

import com.electron.domain.enums.TipoDispositivo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
import lombok.*;

@Entity
@Table(name = "dispositivos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dispositivo_fk_empresa", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ToString.Exclude
    private EmpresaProprietaria empresaProprietaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "dispositivo_tipo", nullable = false)
    private TipoDispositivo dispositivoTipo;

    @Column(name = "dispositivo_marca", length = 100)
    private String dispositivoMarca;

    @Column(name = "dispositivo_modelo", length = 100)
    private String dispositivoModelo;

    @Column(name = "dispositivo_numero_serie", nullable = false, unique = true, length = 50)
    @org.hibernate.annotations.Index(name = "idx_dispositivo_numero_serie")
    private String dispositivoNumeroSerie;

    @Column(name = "dispositivo_ativo")
    private Boolean dispositivoAtivo = true;

    @Column(name = "dispositivo_data_instalacao")
    private LocalDateTime dispositivoDataInstalacao;

    @Column(name = "dispositivo_fk_usuario")
    private Long dispositivoFkUsuario;

    @Column(name = "dispositivo_observacoes", columnDefinition = "TEXT")
    private String dispositivoObservacoes;
}
