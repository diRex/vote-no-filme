package br.com.bluesoft.votenofilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.votenofilme.entity.Filme;
import br.com.bluesoft.votenofilme.exception.ValidacaoException;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;

@Service
public class FilmeServiceImpl implements FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;
	
	
	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.service.FilmeService#votar(java.lang.Long)
	 */
	@Override
	public void votar(Long filmeId) {
		
		Filme filme = this.filmeRepository.findById(filmeId);
		
		if (filme == null) throw new ValidacaoException("O filme que você votou não foi encontrado!");
			
		filme.incrementarVotosEm(1);
		this.filmeRepository.saveOrUpate(filme);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.service.FilmeService#listFilmes()
	 */
	@Override
	public List<Filme> listFilmes() {
		return null;
	}
	
}
