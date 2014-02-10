package br.com.bluesoft.votenofilme.test.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.service.FilmeService;
import br.com.bluesoft.votenofilme.test.BaseTest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@DatabaseSetup("/dataset/filmes.xml")
public class FilmesServiceTest extends BaseTest {
    
    @Autowired
    private FilmeService filmeService;
    
    
    @Test
    @ExpectedDatabase("/dataset/filme1_votado.xml")
    public void should_increment_a_vote_to_movie() {
        Filme filme = this.filmeService.findById(1L);
        this.filmeService.votarEm(filme);
    }
    
    
    @Test
    public void should_list_movies_order_by_ranking() {
        List<Filme> rankingFilmes = this.filmeService.listRankingFilmes();
        Assert.assertNotNull(rankingFilmes);
        Assert.assertFalse(rankingFilmes.isEmpty());
        
        Long votoAnterior = rankingFilmes.get(0).getTotalVotos();
        
        for (Filme filme : rankingFilmes) {
            Assert.assertTrue(votoAnterior >= filme.getTotalVotos());
            votoAnterior = filme.getTotalVotos();
        }
    }
    
}
