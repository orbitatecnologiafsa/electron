package com.electron.mappers;

import com.electron.domain.Pessoa;
import com.electron.domain.dtos.PessoaDTO;
import com.electron.domain.Municipio;
import com.electron.domain.enums.TipoPessoa;
import com.electron.repositories.MunicipioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMapper implements GenericMapper<PessoaDTO, Pessoa> {

    @Autowired
    private MunicipioRepository municipioRepository;

    @Override
    public Pessoa toEntity(PessoaDTO dto) {
        if (dto == null) {
            return null;
        }

        Municipio municipio = municipioRepository.findById(dto.getMunicipioId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Município não encontrado para o ID: " + dto.getMunicipioId()
                ));

        Pessoa pessoa = new Pessoa();
        pessoa.setId(dto.getId());
        pessoa.setTipo(TipoPessoa.valueOf(dto.getTipo()));
        pessoa.setFoto(dto.getFoto());
        pessoa.setEntidade(dto.getEntidade());
        pessoa.setCpfCnpj(dto.getCpfCnpj());
        pessoa.setNomeRazaoSocial(dto.getNomeRazaoSocial());
        pessoa.setNomeFantasia(dto.getNomeFantasia());
        pessoa.setPassaporte(dto.getPassaporte());
        pessoa.setRgInscricaoEstadual(dto.getRgInscricaoEstadual());
        pessoa.setInscricaoMunicipal(dto.getInscricaoMunicipal());
        pessoa.setContato(dto.getContato());
        pessoa.setCep(dto.getCep());
        pessoa.setLogradouro(dto.getLogradouro());
        pessoa.setNumero(dto.getNumero());
        pessoa.setBairro(dto.getBairro());
        pessoa.setComplemento(dto.getComplemento());
        pessoa.setMunicipio(municipio);
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setCelular(dto.getCelular());
        pessoa.setEmail(dto.getEmail());
        pessoa.setDataDeNascimento(dto.getDataDeNascimento());
        pessoa.setObservacoes(dto.getObservacoes());

        return pessoa;
    }

    @Override
    public PessoaDTO toDTO(Pessoa entity) {
        if (entity == null) {
            return null;
        }

        PessoaDTO dto = new PessoaDTO();
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo().name());
        dto.setFoto(entity.getFoto());
        dto.setEntidade(entity.getEntidade());
        dto.setCpfCnpj(entity.getCpfCnpj());
        dto.setNomeRazaoSocial(entity.getNomeRazaoSocial());
        dto.setNomeFantasia(entity.getNomeFantasia());
        dto.setPassaporte(entity.getPassaporte());
        dto.setRgInscricaoEstadual(entity.getRgInscricaoEstadual());
        dto.setInscricaoMunicipal(entity.getInscricaoMunicipal());
        dto.setContato(entity.getContato());
        dto.setCep(entity.getCep());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setBairro(entity.getBairro());
        dto.setComplemento(entity.getComplemento());
        dto.setMunicipioId(entity.getMunicipio() != null ? entity.getMunicipio().getId() : null);
        dto.setTelefone(entity.getTelefone());
        dto.setCelular(entity.getCelular());
        dto.setEmail(entity.getEmail());
        dto.setDataDeNascimento(entity.getDataDeNascimento());
        dto.setObservacoes(entity.getObservacoes());

        return dto;
    }
}
