package com.electron.services;

import com.electron.domain.EmpresaProprietaria;
import com.electron.repositories.EmpresaProprietariaRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaProprietariaService {

    private final EmpresaProprietariaRepository empresaProprietariaRepository;

    public EmpresaProprietariaService(EmpresaProprietariaRepository empresaProprietariaRepository) {
        this.empresaProprietariaRepository = empresaProprietariaRepository;
    }

    public Page<EmpresaProprietaria> listarTodas(Pageable pageable) {
        return empresaProprietariaRepository.findAll(pageable);
    }

    public EmpresaProprietaria listarPorId(Long id) {
        return empresaProprietariaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Empresa proprietária não encontrada"));
    }

    public EmpresaProprietaria criar(EmpresaProprietaria empresaProprietaria) {
        return empresaProprietariaRepository.save(empresaProprietaria);
    }

    public EmpresaProprietaria atualizar(Long id, EmpresaProprietaria empresaProprietaria) {
        EmpresaProprietaria empresaObj = listarPorId(id);

        empresaObj.setTipoUnidade(empresaProprietaria.getTipoUnidade());
        empresaObj.setTipoPessoa(empresaProprietaria.getTipoPessoa());
        empresaObj.setCpfCnpj(empresaProprietaria.getCpfCnpj());
        empresaObj.setVersao(empresaProprietaria.getVersao());
        empresaObj.setRegimeTributario(empresaProprietaria.getRegimeTributario());
        empresaObj.setCnae(empresaProprietaria.getCnae());
        empresaObj.setRazaoSocial(empresaProprietaria.getRazaoSocial());
        empresaObj.setNumeroFilial(empresaProprietaria.getNumeroFilial());
        empresaObj.setDigitoVerificador(empresaProprietaria.getDigitoVerificador());
        empresaObj.setAtivo(empresaProprietaria.getAtivo());
        empresaObj.setNomeFantasia(empresaProprietaria.getNomeFantasia());
        empresaObj.setNomeExibicao(empresaProprietaria.getNomeExibicao());
        empresaObj.setNaturezaJuridica(empresaProprietaria.getNaturezaJuridica());
        empresaObj.setInscricaoEstadual(empresaProprietaria.getInscricaoEstadual());
        empresaObj.setInscricaoMunicipal(empresaProprietaria.getInscricaoMunicipal());
        empresaObj.setContato(empresaProprietaria.getContato());
        empresaObj.setTelefone(empresaProprietaria.getTelefone());
        empresaObj.setEmail(empresaProprietaria.getEmail());
        empresaObj.setCep(empresaProprietaria.getCep());
        empresaObj.setLogradouro(empresaProprietaria.getLogradouro());
        empresaObj.setNumero(empresaProprietaria.getNumero());
        empresaObj.setBairro(empresaProprietaria.getBairro());
        empresaObj.setComplemento(empresaProprietaria.getComplemento());
        empresaObj.setMunicipio(empresaProprietaria.getMunicipio());
        empresaObj.setChaveAcesso(empresaProprietaria.getChaveAcesso());
        empresaObj.setDataCriacao(empresaProprietaria.getDataCriacao());
        empresaObj.setDescricaoAtividades(empresaProprietaria.getDescricaoAtividades());
        empresaObj.setObservacoes(empresaProprietaria.getObservacoes());

        return empresaProprietariaRepository.save(empresaObj);
    }

    public void deletar(Long id) {
        empresaProprietariaRepository.deleteById(id);
    }
}
