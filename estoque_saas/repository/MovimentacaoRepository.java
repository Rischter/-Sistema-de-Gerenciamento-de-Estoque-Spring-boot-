package com.meuProjeto.estoque_saas.repository;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;
import com.meuProjeto.estoque_saas.modelo.Produto;
import org.springframework.data.domain.Pageable; // IMPORTANTE: Nova importação
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    // Sua query original (mantida)
    @Query("SELECT m.produto, SUM(m.quantidade) FROM Movimentacao m " +
           "WHERE m.tipo = com.meuProjeto.estoque_saas.modelo.Movimentacao.TipoMovimentacao.SAIDA " +
           "GROUP BY m.produto ORDER BY SUM(m.quantidade) DESC")
    List<Object[]> totalVendasPorProduto();
    
    // Seu método original (mantido)
    List<Movimentacao> findAllByProduto(Produto produto);

    /**
     * NOVO MÉTODO: Retorna o ranking dos top N produtos com mais movimentações de SAÍDA.
     * Retorna [Nome do Produto, SKU do Produto, Total de Saídas].
     * O limite de 10 será aplicado via Pageable no Service.
     */
    @Query(value = "SELECT m.produto.nome, m.produto.sku, SUM(m.quantidade) AS totalSaidas " +
                   "FROM Movimentacao m " +
                   "WHERE m.tipo = com.meuProjeto.estoque_saas.modelo.Movimentacao.TipoMovimentacao.SAIDA " + 
                   "GROUP BY m.produto.nome, m.produto.sku " + // Agrupa para somar por produto
                   "ORDER BY totalSaidas DESC") // Ordena do maior para o menor
    List<Object[]> findTopNProdutosComMaisSaidas(Pageable pageable);
}