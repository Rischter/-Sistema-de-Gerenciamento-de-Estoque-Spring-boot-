package com.meuProjeto.estoque_saas.modelo.DTO;

import com.meuProjeto.estoque_saas.modelo.Produto;

public class produtoDTO {

    private Long id;
    private String nome;
    private String sku;
    private String categoria;
    private Double custo;
    private Double precoVenda;
    private Integer estoqueAtual;
    private Integer estoqueMinimo;

    // Construtor para JPQL (todos os campos)
    public produtoDTO(Long id, String nome, String sku, String categoria,
                      Double custo, Double precoVenda,
                      Integer estoqueAtual, Integer estoqueMinimo) {
        this.id = id;
        this.nome = nome;
        this.sku = sku;
        this.categoria = categoria;
        this.custo = custo;
        this.precoVenda = precoVenda;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
    }

    // Construtor que recebe um Produto (opcional, útil para outras situações)
    public produtoDTO(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.sku = produto.getSku();
        this.categoria = produto.getCategoria();
        this.custo = produto.getCusto();
        this.precoVenda = produto.getPrecoVenda();
        this.estoqueAtual = produto.getEstoqueAtual();
        this.estoqueMinimo = produto.getEstoqueMinimo();
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Double getCusto() { return custo; }
    public void setCusto(Double custo) { this.custo = custo; }

    public Double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Double precoVenda) { this.precoVenda = precoVenda; }

    public Integer getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(Integer estoqueAtual) { this.estoqueAtual = estoqueAtual; }

    public Integer getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(Integer estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
}


























