package br.com.bluesoft.votenofilme.test.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;
import br.com.bluesoft.votenofilme.test.BaseTest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@DatabaseSetup("/dataset/filmes.xml")
public class FilmesRepositoryTest extends BaseTest {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    
    @Test
    @ExpectedDatabase("/dataset/filme1_votado.xml")
    public void should_update_movie() {
        Filme filme = this.filmeRepository.findById(1L);
        filme.setTotalVotos(3L);
        this.filmeRepository.saveOrUpdate(filme);
    }
    
    
    @Test
    public void should_list_movies_order_by_totalVotos() {
        List<Filme> rankingFilmes = this.filmeRepository.listFilmesOrderByVotos();
        Assert.assertNotNull(rankingFilmes);
        Assert.assertFalse(rankingFilmes.isEmpty());
        
        Long votoAnterior = rankingFilmes.get(0).getTotalVotos();
        
        for (Filme filme : rankingFilmes) {
            Assert.assertTrue(votoAnterior >= filme.getTotalVotos());
            votoAnterior = filme.getTotalVotos();
        }
    }
    
}
