package br.com.bluesoft.votenofilme.integration.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenofilme.entity.Filme;
import br.com.bluesoft.votenofilme.integration.AbstractIntegrationTest;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@DatabaseSetup("/dataset/filmes.xml")
public class FilmeRepositoryTest extends AbstractIntegrationTest {
    
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
        
        for (int i = 0; i < filmes.size() - 1; i++) {
            filme = filmes.get(i);
            Assert.assertTrue(votoAnterior >= filme.getTotalVotos());
            votoAnterior = filme.getTotalVotos();
        }
    }
    
    
    @Test
    public void should_find_movie_by_id() {
        
        Filme filme = this.filmeRepository.findById(1L);
        Assert.assertNotNull(filme);
    }
    
    
    @Test
    @Transactional
    @ExpectedDatabase("/dataset/insert_filme_contantine.xml")
    public void should_insert_a_new_movie() {
        Filme filme = new Filme();
        filme.setNome("Constantine");
        filme.setImagemUrl("constantine.jpg");
        this.filmeRepository.saveOrUpate(filme);
    }
    
    
    @Test
    @ExpectedDatabase("/dataset/update_harry_potter_name.xml")
    public void should_update_a_movie() {
        Filme filme = this.filmeRepository.findById(1L);
        filme.setNome("Harry Potter e a Camara Secreta");
        this.filmeRepository.saveOrUpate(filme);
    }
}
