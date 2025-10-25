package com.meuProjeto.estoque_saas.modelo.DTO;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;
import com.meuProjeto.estoque_saas.modelo.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class RespostaAtualizarDTO {
    private Long id;
    private String nome;
    private String sku;
    private String categoria;
    private Double custo;
    private Double precoVenda;
    private Integer estoqueAtual;
    private Integer estoqueMinimo;
    private List<MovimentacaoDTO> movimentacoes;

    public RespostaAtualizarDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.sku = produto.getSku();
        this.categoria = produto.getCategoria();
        this.custo = produto.getCusto();
        this.precoVenda = produto.getPrecoVenda();
        this.estoqueAtual = produto.getEstoqueAtual();
        this.estoqueMinimo = produto.getEstoqueMinimo();

        // Mapeia as movimentações para DTO
        this.movimentacoes = produto.getMovimentacoes()
                .stream()
                .map(MovimentacaoDTO::new)
                .collect(Collectors.toList());
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getSku() { return sku; }
    public String getCategoria() { return categoria; }
    public Double getCusto() { return custo; }
    public Double getPrecoVenda() { return precoVenda; }
    public Integer getEstoqueAtual() { return estoqueAtual; }
    public Integer getEstoqueMinimo() { return estoqueMinimo; }
    public List<MovimentacaoDTO> getMovimentacoes() { return movimentacoes; }

    // DTO interno para movimentações
    public static class MovimentacaoDTO {
        private Long id;
        private int quantidade;
        private String tipo;
        private String dataHora;

        public MovimentacaoDTO(Movimentacao m) {
            this.id = m.getId();
            this.quantidade = m.getQuantidade();
            this.tipo = m.getTipo().name();
            this.dataHora = m.getDataHora().toString();
        }

        public Long getId() { return id; }
        public int getQuantidade() { return quantidade; }
        public String getTipo() { return tipo; }
        public String getDataHora() { return dataHora; }
    }
}