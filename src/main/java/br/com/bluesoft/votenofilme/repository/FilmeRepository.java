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
	 * @return lista de filmes
	 */
	public List<Filme> listFilmes();
	
}
