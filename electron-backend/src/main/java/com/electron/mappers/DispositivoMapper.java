package com.electron.mappers;

import com.electron.domain.Dispositivo;
import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.dtos.DispositivoDTO;
import com.electron.repositories.EmpresaProprietariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DispositivoMapper implements GenericMapper<DispositivoDTO, Dispositivo> {

    @Autowired
    private EmpresaProprietariaRepository empresaProprietariaRepository;

    @Override
    public Dispositivo toEntity(DispositivoDTO dto) {
        if (dto == null) return null;

        // Buscar a empresa proprietária pelo ID
        EmpresaProprietaria empresaProprietaria = empresaProprietariaRepository
                .findById(dto.getEmpresaProprietariaId())
                .orElse(null);  // Caso a empresa não seja encontrada, retorna null

        return new Dispositivo(
                null,
                empresaProprietaria, // Aqui a entidade é atribuída
                dto.getDispositivoTipo(),
                dto.getDispositivoMarca(),
                dto.getDispositivoModelo(),
                dto.getDispositivoNumeroSerie(),
                dto.getDispositivoAtivo(),
                dto.getDispositivoDataInstalacao(),
                dto.getDispositivoFkUsuario(),
                dto.getDispositivoObservacoes()
        );
    }

    @Override
    public DispositivoDTO toDTO(Dispositivo entity) {
        if (entity == null) return null;
        return new DispositivoDTO(
                entity.getId(),
                entity.getEmpresaProprietaria() != null ? entity.getEmpresaProprietaria().getId() : null, // Passa o ID da empresa
                entity.getDispositivoTipo(),
                entity.getDispositivoMarca(),
                entity.getDispositivoModelo(),
                entity.getDispositivoNumeroSerie(),
                entity.getDispositivoAtivo(),
                entity.getDispositivoDataInstalacao(),
                entity.getDispositivoFkUsuario(),
                entity.getDispositivoObservacoes()
        );
    }
}