package br.com.bluesoft.votenofilme.service;

import java.util.List;

import br.com.bluesoft.votenofilme.entity.Filme;


public interface FilmeService {
	
	
	/**
	 * Incrementa um voto para o filme
	 * 
	 * @param filmeId - id do filme que irá receber o voto
	 */
	public void votar(Long filmeId);
	
	/**
	 * Obtém todos os filmes
	 * 
	 * @return lista de filmes
	 */
	public List<Filme> listFilmes();
}