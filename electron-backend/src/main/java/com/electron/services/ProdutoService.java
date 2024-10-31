package com.electron.services;

import com.electron.domain.Produto;
import com.electron.repositories.ProdutoRepository;
import com.electron.services.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado com o ID: " + id));
    }

    // Criar novo produto
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto existente
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setCodigo(produtoAtualizado.getCodigo());
                    produto.setBarras(produtoAtualizado.getBarras());
                    produto.setNome(produtoAtualizado.getNome());
                    produto.setDescricao(produtoAtualizado.getDescricao());
                    produto.setUnidadeEntrada(produtoAtualizado.getUnidadeEntrada());
                    produto.setUnidadeSaida(produtoAtualizado.getUnidadeSaida());
                    produto.setUnidadeEstocagem(produtoAtualizado.getUnidadeEstocagem());
                    produto.setUnidadeTributacao(produtoAtualizado.getUnidadeTributacao());
                    produto.setFatorConversao(produtoAtualizado.getFatorConversao());
                    produto.setGrupoProdServ(produtoAtualizado.getGrupoProdServ());
                    produto.setClassificacao(produtoAtualizado.getClassificacao());
                    produto.setDiasValidade(produtoAtualizado.getDiasValidade());
                    produto.setTipoControle(produtoAtualizado.getTipoControle());
                    produto.setPrecoCusto(produtoAtualizado.getPrecoCusto());
                    produto.setPrecoCustoMedio(produtoAtualizado.getPrecoCustoMedio());
                    produto.setPrecoMargemLucro(produtoAtualizado.getPrecoMargemLucro());
                    produto.setPrecoVenda(produtoAtualizado.getPrecoVenda());
                    produto.setPrecoRevenda(produtoAtualizado.getPrecoRevenda());
                    produto.setSaldo(produtoAtualizado.getSaldo());
                    produto.setBloqueado(produtoAtualizado.getBloqueado());
                    produto.setPedidoVenda(produtoAtualizado.getPedidoVenda());
                    produto.setDisponivel(produtoAtualizado.getDisponivel());
                    produto.setSaldoIdeal(produtoAtualizado.getSaldoIdeal());
                    produto.setCodigoAjuste(produtoAtualizado.getCodigoAjuste());
                    produto.setCodigoCestNcm(produtoAtualizado.getCodigoCestNcm());
                    produto.setTributacaoEstadual(produtoAtualizado.getTributacaoEstadual());
                    produto.setTributacaoFederal(produtoAtualizado.getTributacaoFederal());
                    produto.setObservacoes(produtoAtualizado.getObservacoes());
                    produto.setInformacaoExtraBalanca(produtoAtualizado.getInformacaoExtraBalanca());
                    produto.setUnidadeEmbalagem(produtoAtualizado.getUnidadeEmbalagem());
                    produto.setQuantidadeEmbalagem(produtoAtualizado.getQuantidadeEmbalagem());
                    produto.setCodigoAnp(produtoAtualizado.getCodigoAnp());
                    produto.setReferencia(produtoAtualizado.getReferencia());
                    produto.setLocalizacao(produtoAtualizado.getLocalizacao());
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new NotFoundException("Produto não encontrado com o ID: " + id));
    }

    // Excluir produto por ID
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

}
