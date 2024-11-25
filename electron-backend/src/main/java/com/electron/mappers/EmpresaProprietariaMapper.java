package com.electron.mappers;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Municipio;
import com.electron.domain.dtos.EmpresaProprietariaDTO;
import com.electron.repositories.MunicipioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaProprietariaMapper implements GenericMapper<EmpresaProprietariaDTO, EmpresaProprietaria> {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public EmpresaProprietaria toEntity(EmpresaProprietariaDTO dto) {
        if (dto == null) {
            return null;
        }

        Municipio municipio = municipioRepository.findById(dto.getMunicipioId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Município não encontrado para o ID: " + dto.getMunicipioId()
                ));

        EmpresaProprietaria empresa = new EmpresaProprietaria();
        empresa.setId(dto.getId());
        empresa.setTipoUnidade(dto.getTipoUnidade());
        empresa.setTipoPessoa(dto.getTipoPessoa());
        empresa.setCpfCnpj(dto.getCpfCnpj());
        empresa.setVersao(dto.getVersao());
        empresa.setRegimeTributario(dto.getRegimeTributario());
        empresa.setCnae(dto.getCnae());
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNumeroFilial(dto.getNumeroFilial());
        empresa.setDigitoVerificador(dto.getDigitoVerificador());
        empresa.setAtivo(dto.getAtivo());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setNomeExibicao(dto.getNomeExibicao());
        empresa.setNaturezaJuridica(dto.getNaturezaJuridica());
        empresa.setInscricaoEstadual(dto.getInscricaoEstadual());
        empresa.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        empresa.setContato(dto.getContato());
        empresa.setTelefone(dto.getTelefone());
        empresa.setEmail(dto.getEmail());
        empresa.setCep(dto.getCep());
        empresa.setLogradouro(dto.getLogradouro());
        empresa.setNumero(dto.getNumero());
        empresa.setBairro(dto.getBairro());
        empresa.setComplemento(dto.getComplemento());
        empresa.setMunicipio(municipio);
        empresa.setChaveAcesso(dto.getChaveAcesso());
        empresa.setDataCriacao(dto.getDataCriacao());
        empresa.setDescricaoAtividades(dto.getDescricaoAtividades());
        empresa.setObservacoes(dto.getObservacoes());

        return empresa;
    }

    @Override
    public EmpresaProprietariaDTO toDTO(EmpresaProprietaria entity) {
        if (entity == null) {
            return null;
        }

        EmpresaProprietariaDTO dto = new EmpresaProprietariaDTO();
        dto.setId(entity.getId());
        dto.setTipoUnidade(entity.getTipoUnidade());
        dto.setTipoPessoa(entity.getTipoPessoa());
        dto.setCpfCnpj(entity.getCpfCnpj());
        dto.setVersao(entity.getVersao());
        dto.setRegimeTributario(entity.getRegimeTributario());
        dto.setCnae(entity.getCnae());
        dto.setRazaoSocial(entity.getRazaoSocial());
        dto.setNumeroFilial(entity.getNumeroFilial());
        dto.setDigitoVerificador(entity.getDigitoVerificador());
        dto.setAtivo(entity.getAtivo());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setNomeExibicao(entity.getNomeExibicao());
        dto.setNaturezaJuridica(entity.getNaturezaJuridica());
        dto.setInscricaoEstadual(entity.getInscricaoEstadual());
        dto.setInscricaoMunicipal(entity.getInscricaoMunicipal());
        dto.setContato(entity.getContato());
        dto.setTelefone(entity.getTelefone());
        dto.setEmail(entity.getEmail());
        dto.setCep(entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setBairro(entity.getBairro());
        dto.setComplemento(entity.getComplemento());
        dto.setMunicipioId(entity.getMunicipio() != null ? entity.getMunicipio().getId() : null);
        dto.setChaveAcesso(entity.getChaveAcesso());
        dto.setDataCriacao(entity.getDataCriacao());
        dto.setDescricaoAtividades(entity.getDescricaoAtividades());
        dto.setObservacoes(entity.getObservacoes());

        return dto;
    }
} 