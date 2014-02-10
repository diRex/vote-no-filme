package br.com.bluesoft.votenofilme.service;

import java.util.List;

import br.com.bluesoft.votenofilme.model.Filme;

public interface FilmeService {
    
    /**
     * Incrementa um voto para o filme
     * 
     * @param filme - filme que irá receber o voto
     */
    void votarEm(Filme filme);
    
    
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
