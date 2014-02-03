package br.com.bluesoft.votenofilme.service;

import java.util.List;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl.Comparacao;
import br.com.bluesoft.votenofilme.model.Filme;

public interface FilmeService {
    
    /**
     * Incrementa um voto para o filme
     * 
     * @param filmeId - id do filme que irá receber o voto
     */
    void votarEm(long filmeId);
    
    
    /**
     * Obtém uma nova comparacao entre dois filmes que ainda não foram comparadas
     * 
     * @return comparacao
     */
    Comparacao obterComparacao();
    
    
    /**
     * Obtém uma nova comparacao entre o filme votado na comparação anterior e outro filme que ainda não foi comparado
     * 
     * @param filme - filme votado na comparação anterior
     * @return comparacao
     */
    Comparacao obterComparacaoCom(Filme filme);
    
    
    /**
     * Obtém lista de filmes ordenados por total de votos
     * 
     * @return lista de filmes
     */
    List<Filme> listRankingFilmes();
    
    
    /**
     * Obtém um filme pelo Id
     * 
     * @param filmeId
     * @return filme
     */
    Filme findById(long filmeId);
}
