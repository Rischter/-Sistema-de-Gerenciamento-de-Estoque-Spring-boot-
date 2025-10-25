package com.meuProjeto.estoque_saas.Service;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;
import com.meuProjeto.estoque_saas.modelo.Produto;
import com.meuProjeto.estoque_saas.modelo.DTO.RespostaAtualizarDTO;
import com.meuProjeto.estoque_saas.modelo.DTO.produtoDTO;
import com.meuProjeto.estoque_saas.repository.MovimentacaoRepository;
import com.meuProjeto.estoque_saas.repository.ProdutoRepository;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.EstoqueInsuficienteException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtorepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public ProdutoService(ProdutoRepository produtorepository, MovimentacaoRepository movimentacaoRepository) {
        this.produtorepository = produtorepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    @Transactional
    public Produto salvaProduto(Produto produto) {
        validarProduto(produto);

        Produto pSalvo = produtorepository.save(produto);

        if (pSalvo.getEstoqueAtual() > 0) {
            Movimentacao mov = new Movimentacao(
                    pSalvo,
                    pSalvo.getEstoqueAtual(),
                    Movimentacao.TipoMovimentacao.ENTRADA
            );
            movimentacaoRepository.save(mov);
        }

        double margem = pSalvo.getPrecoVenda() - pSalvo.getCusto();
        System.out.println("Margem de lucro do produto " + pSalvo.getNome() + ": " + margem);

        return pSalvo;
    }

    public List<produtoDTO> listarProdutos(
            Long id,
            String nome,
            String sku,
            String categoria,
            Double custo,
            Double precoVenda,
            Integer estoqueAtual,
            Integer estoqueMinimo
    ) {
        List<Produto> produtos = produtorepository.findAll();

        return produtos.stream()
                .filter(p -> id == null || p.getId().equals(id))
                .filter(p -> nome == null || (p.getNome() != null && p.getNome().toLowerCase().contains(nome.toLowerCase())))
                .filter(p -> sku == null || (p.getSku() != null && p.getSku().toLowerCase().contains(sku.toLowerCase())))
                .filter(p -> categoria == null || (p.getCategoria() != null && p.getCategoria().toLowerCase().contains(categoria.toLowerCase())))
                .filter(p -> custo == null || p.getCusto().equals(custo))
                .filter(p -> precoVenda == null || p.getPrecoVenda().equals(precoVenda))
                .filter(p -> estoqueAtual == null || p.getEstoqueAtual().equals(estoqueAtual))
                .filter(p -> estoqueMinimo == null || p.getEstoqueMinimo().equals(estoqueMinimo))
                .map(p -> new produtoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getSku(),
                        p.getCategoria(),
                        p.getCusto(),
                        p.getPrecoVenda(),
                        p.getEstoqueAtual(),
                        p.getEstoqueMinimo()
                ))
                .toList();
    }

    public Produto buscarPorId(Long id) {
        return produtorepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Transactional
    public RespostaAtualizarDTO atualizarEstoque(Long id, int quantidade, Movimentacao.TipoMovimentacao tipo) {
        Produto produto = buscarPorId(id);

        if (tipo == Movimentacao.TipoMovimentacao.SAIDA) {
            quantidade = -quantidade;
        }

        int novoEstoque = produto.getEstoqueAtual() + quantidade;
        if (novoEstoque < 0) {
            throw new EstoqueInsuficienteException(produto.getNome());
        }

        produto.setEstoqueAtual(novoEstoque);

        Movimentacao mov = new Movimentacao(produto, Math.abs(quantidade), tipo);
        movimentacaoRepository.save(mov);
        produto.getMovimentacoes().add(mov);

        verificarEstoqueMinimo(produto);

        Produto atualizado = produtorepository.save(produto);

        return new RespostaAtualizarDTO(atualizado);
    }

    private void verificarEstoqueMinimo(Produto produto) {
        if (produto.getEstoqueAtual() <= produto.getEstoqueMinimo()) {
            System.out.println("Alerta: a quantidade do produto " + produto.getNome() + " está baixa!");
        }
    }

    @Transactional
    public void deletarProduto(Long id) {
        Produto produto = produtorepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        produtorepository.delete(produto);
    }

    private void validarProduto(Produto produto) {
        if (produto.getEstoqueAtual() < 0)
            throw new IllegalArgumentException("O estoque não pode ser negativo");
        if (produto.getNome() == null || produto.getNome().isEmpty())
            throw new IllegalArgumentException("O nome do produto é obrigatório");
        if (produto.getPrecoVenda() == null || produto.getPrecoVenda() <= 0)
            throw new IllegalArgumentException("Preço de venda inválido");
        if (produto.getCusto() < 0)
            throw new IllegalArgumentException("O custo não pode ser negativo");
    }
}































































































