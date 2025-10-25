package com.meuProjeto.estoque_saas.controller;

import com.meuProjeto.estoque_saas.Service.MovimentacaoService;
import com.meuProjeto.estoque_saas.modelo.DTO.MovimentacaoDTO;
import org.springframework.http.ResponseEntity; // Importação necessária para ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    public List<MovimentacaoDTO> listarTodas() {
        return movimentacaoService.listarTodas();
    }
    
    // NOVO ENDPOINT: Retorna o ranking dos Top 10 produtos com mais saídas
    @GetMapping("/ranking/saidas")
    public ResponseEntity<List<Object[]>> getTop10Saidas() {
        // O método retorna List<Object[]> pois o Repository faz uma consulta agregada
        List<Object[]> ranking = movimentacaoService.findTop10ProdutosComMaisSaidas();
        
        // Retorna 200 OK com o ranking
        return ResponseEntity.ok(ranking);
    }
}