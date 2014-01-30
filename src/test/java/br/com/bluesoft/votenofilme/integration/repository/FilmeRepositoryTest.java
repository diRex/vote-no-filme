package br.com.bluesoft.votenofilme.integration.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.entity.Filme;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;

import com.github.springtestdbunit.annotation.DatabaseSetup;

@DatabaseSetup("/dataset/filmes.xml")
public class FilmeRepositoryTest extends AbstractRepositoryTest {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Test
	public void should_list_all_movies() {
		List<Filme> filmes = this.filmeRepository.listFilmes();
		Assert.assertNotNull(filmes);
		Assert.assertFalse(filmes.isEmpty());
	}
	
	@Test
	public void should_list_movies_order_by_votes() {
		List<Filme> filmes = this.filmeRepository.listRankingFilmes();
		
		Assert.assertNotNull(filmes);
		Assert.assertFalse(filmes.isEmpty());
		
		Long votoAnterior = filmes.get(0).getTotalVotos();
		Filme filme;
		
		for(int i = 0; i < filmes.size()-1 ; i++) {
			filme = filmes.get(i);
			Assert.assertTrue(votoAnterior >= filme.getTotalVotos());
			votoAnterior = filme.getTotalVotos();
		}
	}
	
	@Test
	public void shoul_find_movie_by_id() {
		Filme filme = this.filmeRepository.findById(1L);
		Assert.assertNotNull(filme);
	}
}
