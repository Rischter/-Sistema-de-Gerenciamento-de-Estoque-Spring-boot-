package com.meuProjeto.estoque_saas.modelo;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id ;

    // CORREÇÃO: Adicionando columnDefinition para garantir o tipo TEXT no PostgreSQL
    @Column(columnDefinition = "TEXT")
    private String nome;
    
    // CORREÇÃO: Adicionando columnDefinition para garantir o tipo VARCHAR/TEXT
    @Column(columnDefinition = "VARCHAR(50)")
    private String sku;
    
    // CORREÇÃO: Adicionando columnDefinition para garantir o tipo TEXT
    @Column(columnDefinition = "TEXT")
    private String categoria;
    
    // ATENÇÃO: Se 'custo' for 'double' primitivo, ele não pode ser nulo.
    // Mantenho 'Double' (Wrapper) como está nos seus getters/setters para suportar 'null' nos filtros.
    private Double custo; 
    
    private Double precoVenda;
    private Integer estoqueAtual;
    private Integer estoqueMinimo;


    // --- Getters e Setters ---

    // AVISO: Mudei o nome do setter de 'setID' para 'setId' (convenção Java)
    public Long getId(){
        return Id;
    }

    public void setId(Long Id){
        this.Id = Id;
    }

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


    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimentacao> movimentacoes;

    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }
}



















































































