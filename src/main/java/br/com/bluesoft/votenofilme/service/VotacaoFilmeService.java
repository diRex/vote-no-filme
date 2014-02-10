package br.com.bluesoft.votenofilme.service;

import java.util.List;
import java.util.Map;

import br.com.bluesoft.votenofilme.model.Filme;

public interface VotacaoFilmeService {
    
    /**
     * Incrementa um voto para o filme
     * 
     * @param filme - filme que irá receber o voto
     */
    void votarEm(Filme filme);
    
    
    /**
     * Obtem lista de filmes ordenados por total de votos
     * 
     * @return lista de filmes
     */
    List<Filme> listRankingFilmes();
    
    /**
     * Carrega as opcoes de filmes para votar
     */
    void loadOpcoesFilmes();


    /**
     * Obtem nova comparacao com filme parametrizado
     * @param filme
     * 
     * @return map com indece da opcao e filme
     */
    Map<Integer, Filme> getNovaComparacaoCom(Filme filme);


    /**
     * Obtem nova comparacao entre dois filmes aleatorios
     * 
     * @return map com indece da opcao e filme
     */
    Map<Integer, Filme> getNovaComparacao();
    
}
