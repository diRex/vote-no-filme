package br.com.bluesoft.votenofilme.service;


public interface FilmeService {
	
	
	/**
	 * Incrementa um voto para o filme
	 * 
	 * @param filmeId - id do filme que irá receber o voto
	 */
	public void votar(Long filmeId);
	
	
}
