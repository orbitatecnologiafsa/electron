package com.electron.mappers;

import com.electron.domain.EmpresaProprietaria;
import com.electron.domain.Municipio;
import org.springframework.stereotype.Component;

import com.electron.domain.Vendedor;
import com.electron.domain.dtos.VendedorDTO;

@Component
public class VendedorMapper implements GenericMapper<VendedorDTO, Vendedor> {
    
    @Override
    public Vendedor toEntity(VendedorDTO dto) {
        if (dto == null) return null;

        Municipio municipio = new Municipio();
        municipio.setId(dto.getMunicipioId());

        EmpresaProprietaria empresa = new EmpresaProprietaria();
        empresa.setId(dto.getEmpresaId());

        Vendedor vendedor = new Vendedor(
            dto.getTipo(),
            dto.getFoto(),
            dto.getEntidade(),
            dto.getCpfCnpj(),
            dto.getNomeRazaoSocial(),
            dto.getNomeFantasia(),
            dto.getPassaporte(),
            dto.getRgInscricaoEstadual(),
            dto.getInscricaoMunicipal(),
            dto.getContato(),
            dto.getCep(),
            dto.getLogradouro(),
            dto.getNumero(),
            dto.getBairro(),
            dto.getComplemento(),
            municipio,
            dto.getTelefone(),
            dto.getCelular(),
            dto.getEmail(),
            dto.getDataDeNascimento(),
            dto.getObservacoes(),
            empresa,
            dto.getDesconto(),
            dto.getComissao(),
            dto.getTipoComissao(),
            dto.getBaseCalculo(),
            dto.getObservacoes()
        );
        
        vendedor.setId(dto.getId());
        return vendedor;
    }
    
    @Override
    public VendedorDTO toDTO(Vendedor entity) {
        if (entity == null) return null;
        VendedorDTO dto = new VendedorDTO();
        
        dto.setId(entity.getId());
        dto.setTipo(entity.getTipo());
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
        dto.setEmpresaId(entity.getEmpresa() != null ? entity.getEmpresa().getId() : null);
        dto.setDesconto(entity.getDesconto());
        dto.setComissao(entity.getComissao());
        dto.setTipoComissao(entity.getTipoComissao());
        dto.setBaseCalculo(entity.getBaseCalculo());
        dto.setObservacoes(entity.getObservacoes());
        
        return dto;
    }
} 