package br.com.bluesoft.votenofilme.integration.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.entity.Filme;
import br.com.bluesoft.votenofilme.integration.AbstractIntegrationTest;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;
import br.com.bluesoft.votenofilme.service.FilmeService;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

public class FilmesServiceIntegrationTest extends AbstractIntegrationTest {
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    
    @Test
    @DatabaseSetup("/dataset/filmes.xml")
    @ExpectedDatabase("/dataset/filme1_votado.xml")
    public void should_increment_a_vote_to_movie() {
        Filme filme = this.filmeRepository.findById(1L);
        this.filmeService.votarEm(filme);
    }
    
}
