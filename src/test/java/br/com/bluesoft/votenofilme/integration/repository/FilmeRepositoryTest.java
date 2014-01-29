package br.com.bluesoft.votenofilme.integration.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.repository.FilmeRepository;

public class FilmeRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Test
	public void test_list_ranking_filmes() {
		this.filmeRepository.listRankingFilmes();
	}
	
}
