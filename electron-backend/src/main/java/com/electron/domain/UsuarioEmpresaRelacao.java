package com.electron.domain;

import com.electron.domain.enums.TipoAcesso;
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
@Table(name = "usuario_empresa_relacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpresaRelacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_empresa_relacao_id")
    private Integer id;

    @Column(name = "relacao_fk_usuario_empresa")
    private Integer usuarioEmpresaId;

    @Column(name = "relacao_fk_empresa", nullable = false)
    private Integer empresaId;

    @Enumerated(EnumType.STRING)
    @Column(name = "relacao_tipo_acesso", nullable = false)
    private TipoAcesso tipoAcesso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEmpresa usuarioEmpresa;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaProprietaria empresaProprietaria;

}
