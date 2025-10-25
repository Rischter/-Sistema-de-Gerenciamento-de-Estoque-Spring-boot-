package com.meuProjeto.estoque_saas.modelo;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Movimentacao {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private int quantidade;
    @Enumerated(EnumType.STRING)   
    private TipoMovimentacao tipo;
    private LocalDateTime dataHora;
   @ManyToOne
@JoinColumn(name = "produto_id", nullable = false)
private Produto produto;


//enum para o tipo de movimentação

public enum TipoMovimentacao {
        ENTRADA,
        SAIDA
    }



   public Movimentacao() {}


    public Movimentacao(Produto produto, int quantidade, TipoMovimentacao tipo) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = LocalDateTime.now();
    }


   // Getters
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

public Long getId() {
    return id;
}





































}