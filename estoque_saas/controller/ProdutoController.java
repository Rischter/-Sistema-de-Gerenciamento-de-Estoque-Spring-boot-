package com.meuProjeto.estoque_saas.controller;

import com.meuProjeto.estoque_saas.Service.ProdutoService;
import com.meuProjeto.estoque_saas.modelo.Produto;
import com.meuProjeto.estoque_saas.modelo.DTO.AtualizarEstoqueDTO;
import com.meuProjeto.estoque_saas.modelo.DTO.RespostaAtualizarDTO;
import com.meuProjeto.estoque_saas.modelo.DTO.produtoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto salvo = produtoService.salvaProduto(produto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping
    public List<produtoDTO> listarProdutos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double custo,
            @RequestParam(required = false) Double precoVenda,
            @RequestParam(required = false) Integer estoqueAtual,
            @RequestParam(required = false) Integer estoqueMinimo
    ) {
        return produtoService.listarProdutos(id, nome, sku, categoria, custo, precoVenda, estoqueAtual, estoqueMinimo);
    }

    @PutMapping("/{id}/estoque")
    public ResponseEntity<RespostaAtualizarDTO> atualizarEstoque(
            @PathVariable Long id,
            @RequestBody AtualizarEstoqueDTO dto
    ) {
        RespostaAtualizarDTO atualizado = produtoService.atualizarEstoque(id, dto.getQuantidade(), dto.getTipo());
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}


















































































