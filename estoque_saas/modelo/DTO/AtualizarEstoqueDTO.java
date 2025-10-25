package com.meuProjeto.estoque_saas.modelo.DTO;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;


public class AtualizarEstoqueDTO {

    private int quantidade;
    private Movimentacao.TipoMovimentacao tipo;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Movimentacao.TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(Movimentacao.TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
}