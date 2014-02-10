package br.com.bluesoft.votenofilme.repository;

import java.util.List;

import br.com.bluesoft.votenofilme.model.Filme;


public interface FilmeRepository {
    
    /**
     * Obtem um filme pelo Id
     * 
     * @param filmeId id do filme
     * @return filme
     */
    Filme findById(long filmeId);
    
    /**
     * Obtem filmes ordenados por votos
     * 
     * @return filmes ordenados por voto
     */
    List<Filme> listFilmesOrderByVotos();

    /**
     * Salva ou atualiza um filmes
     * 
     * @param filme filme a ser salvo ou atualizado
     * @return filme
     */
    Filme saveOrUpdate(Filme filme);
}
