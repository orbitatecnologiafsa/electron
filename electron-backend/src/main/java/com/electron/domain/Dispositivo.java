package com.electron.domain;

import com.electron.domain.enums.TipoDispositivo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private EmpresaProprietaria empresaProprietaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "dispositivo_tipo", nullable = false)
    private TipoDispositivo dispositivoTipo;

    @Column(name = "dispositivo_marca", length = 100)
    private String dispositivoMarca;

    @Column(name = "dispositivo_modelo", length = 100)
    private String dispositivoModelo;

    @Column(name = "dispositivo_numero_serie", nullable = false, unique = true, length = 50)
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
