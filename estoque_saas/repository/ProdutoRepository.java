package com.meuProjeto.estoque_saas.repository;

import com.meuProjeto.estoque_saas.modelo.Produto;
import com.meuProjeto.estoque_saas.modelo.DTO.produtoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("""
        SELECT new com.meuProjeto.estoque_saas.modelo.DTO.produtoDTO(
            p.id,
            p.nome,
            p.sku,
            p.categoria,
            p.custo,
            p.precoVenda,
            p.estoqueAtual,
            p.estoqueMinimo
        )
        FROM Produto p
        WHERE
            (:id IS NULL OR p.id = :id)
            AND (:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
            AND (:sku IS NULL OR LOWER(p.sku) LIKE LOWER(CONCAT('%', :sku, '%')))
            AND (:categoria IS NULL OR LOWER(p.categoria) LIKE LOWER(CONCAT('%', :categoria, '%')))
            AND (:custo IS NULL OR p.custo = :custo)
            AND (:precoVenda IS NULL OR p.precoVenda = :precoVenda)
            AND (:estoqueAtual IS NULL OR p.estoqueAtual = :estoqueAtual)
            AND (:estoqueMinimo IS NULL OR p.estoqueMinimo = :estoqueMinimo)
    """)
    List<produtoDTO> filtrarProdutos(
            @Param("id") Long id,
            @Param("nome") String nome,
            @Param("sku") String sku,
            @Param("categoria") String categoria,
            @Param("custo") Double custo,
            @Param("precoVenda") Double precoVenda,
            @Param("estoqueAtual") Integer estoqueAtual,
            @Param("estoqueMinimo") Integer estoqueMinimo
    );
}