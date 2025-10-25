package com.meuProjeto.estoque_saas.modelo.DTO;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;

import java.time.LocalDateTime;

public class MovimentacaoDTO {

    private Long id;
    private Long produtoId;
    private String produtoNome;
    private String produtoSku;
    private int quantidade;
    private String tipo;
    private LocalDateTime dataHora;

    public MovimentacaoDTO(Movimentacao m) {
        this.id = m.getId();
        this.produtoId = m.getProduto().getId();
        this.produtoNome = m.getProduto().getNome();
        this.produtoSku = m.getProduto().getSku();
        this.quantidade = m.getQuantidade();
        
        // >>> CORREÇÃO APLICADA AQUI: Tratamento de NullPointerException <<<
        // Se m.getTipo() for null, atribui a string "TIPO_NAO_DEFINIDO" em vez de quebrar.
        this.tipo = m.getTipo() != null ? m.getTipo().name() : "TIPO_NAO_DEFINIDO";
        
        this.dataHora = m.getDataHora();
    }

    // Getters
    public Long getId() { return id; }
    public Long getProdutoId() { return produtoId; }
    public String getProdutoNome() { return produtoNome; }
    public String getProdutoSku() { return produtoSku; }
    public int getQuantidade() { return quantidade; }
    public String getTipo() { return tipo; }
    public LocalDateTime getDataHora() { return dataHora; }
}