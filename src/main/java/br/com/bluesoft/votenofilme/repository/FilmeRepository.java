package br.com.bluesoft.votenofilme.repository;

import java.util.List;

import br.com.bluesoft.votenofilme.entity.Filme;

public interface FilmeRepository {
    
    /**
     * Salva ou atualiza um filme
     * 
     * @param filme
     * @return filme salvo com id
     */
    Filme saveOrUpate(Filme filme);
    
    
    /**
     * Obtém lista de filmes
     * 
     * @param orderBy(opcional) - campo usado para ordenação
     * @return lista de filmes
     */
    List<Filme> listFilmes();
    
    
    /**
     * Obtém um filme pelo ID
     * 
     * @param filmeId - ID do filme
     * @return filme
     */
    Filme findById(Long filmeId);
    
    
    /**
     * Obtém o ranking dos filmes
     * 
     * @return lista de filmes ordenados por total de votos
     */
    List<Filme> listRankingFilmes();
    
}
