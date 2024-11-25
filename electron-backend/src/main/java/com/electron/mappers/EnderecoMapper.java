package com.electron.mappers;

import com.electron.domain.Endereco;
import com.electron.domain.Municipio;
import com.electron.domain.Pessoa;
import com.electron.domain.dtos.EnderecoDTO;
import com.electron.repositories.MunicipioRepository;
import com.electron.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper implements GenericMapper<EnderecoDTO, Endereco> {

    @Autowired
    private PessoaRepository pessoaRepository;  // Repositório para a Pessoa

    @Autowired
    private MunicipioRepository municipioRepository;  // Repositório para o Município

    @Override
    public Endereco toEntity(EnderecoDTO dto) {
        if (dto == null) return null;

        // Buscar a pessoa e o município pelo ID
        Pessoa pessoa = pessoaRepository
                .findById(dto.getPessoaId())
                .orElse(null);  // Caso não encontre, atribui null

        Municipio municipio = municipioRepository
                .findById(dto.getMunicipioId())
                .orElse(null);  // Caso não encontre, atribui null

        // Criar e retornar a entidade Endereco
        return new Endereco(
                null,
                dto.getTipoEndereco(),
                dto.getCep(),
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getBairro(),
                dto.getComplemento(),
                dto.getTelefone(),
                pessoa,   // Associa a pessoa
                municipio  // Associa o município
        );
    }

    @Override
    public EnderecoDTO toDTO(Endereco entity) {
        if (entity == null) return null;

        // Criar e retornar o DTO com os IDs das entidades associadas
        return new EnderecoDTO(
                entity.getId(),
                entity.getTipoEndereco(),
                entity.getCep(),
                entity.getLogradouro(),
                entity.getNumero(),
                entity.getBairro(),
                entity.getComplemento(),
                entity.getTelefone(),
                entity.getPessoa() != null ? entity.getPessoa().getId() : null,  // Passa o ID da Pessoa
                entity.getMunicipio() != null ? entity.getMunicipio().getId() : null  // Passa o ID do Município
        );
    }
} 