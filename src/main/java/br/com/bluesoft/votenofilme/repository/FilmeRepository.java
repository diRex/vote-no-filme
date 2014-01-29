package br.com.bluesoft.votenofilme.repository;

import java.util.List;

import br.com.bluesoft.votenofilme.entity.Filme;

/**
 * @author direx
 *
 */
public interface FilmeRepository {
	
	/**
	 * Salva ou atualiza um filme
	 * @param filme 
	 * @return filme salvo com id
	 */
	public Filme saveOrUpate(Filme filme);
	
	/**
	 * Obtém todos os filmes
	 * @param orderBy(opcional) - campo usado para ordenação 
	 * @return lista de filmes
	 */
	public List<Filme> listFilmes();

	/**
	 * Obtém um filme pelo ID
	 * @param filmeId - ID do filme
	 * @return filme
	 */
	public Filme findById(Long filmeId);
	
	/**
	 * Obtém o ranking dos filmes
	 * @return lista de filmes ordenados por total de votos
	 */
	public List<Filme> listRankingFilmes();
	
}
