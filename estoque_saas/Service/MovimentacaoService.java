

package com.meuProjeto.estoque_saas.Service;

import com.meuProjeto.estoque_saas.modelo.Movimentacao;
import com.meuProjeto.estoque_saas.modelo.DTO.MovimentacaoDTO;
import com.meuProjeto.estoque_saas.repository.MovimentacaoRepository;
import org.springframework.data.domain.PageRequest;  // Importação necessária
import org.springframework.data.domain.Pageable;   // Importação necessária
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<MovimentacaoDTO> listarTodas() {
        return movimentacaoRepository.findAll()
                .stream()
                .map(MovimentacaoDTO::new)
                .toList();
    }
    
    // NOVO MÉTODO: Retorna o ranking dos top 10 produtos mais vendidos
    /**
     * Retorna o ranking dos top 10 produtos com mais movimentações de SAÍDA.
     * @return Uma lista de Object[]: [Nome do Produto, SKU, Total de Saídas]
     */
    public List<Object[]> findTop10ProdutosComMaisSaidas() {
        // Cria um objeto Pageable que limita o resultado a 10 elementos (página 0, tamanho 10)
        Pageable topTen = PageRequest.of(0, 10);
        return movimentacaoRepository.findTopNProdutosComMaisSaidas(topTen);
    }
}