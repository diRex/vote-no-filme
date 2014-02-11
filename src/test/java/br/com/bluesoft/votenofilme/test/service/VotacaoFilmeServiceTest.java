package br.com.bluesoft.votenofilme.test.service;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl;
import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;
import br.com.bluesoft.votenofilme.service.VotacaoFilmeService;
import br.com.bluesoft.votenofilme.test.BaseTest;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@DatabaseSetup("/dataset/filmes.xml")
public class VotacaoFilmeServiceTest extends BaseTest {
    
    @Autowired
    private VotacaoFilmeService votacaoFilmeService;
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    
    @Before
    public void setUp() {
        votacaoFilmeService.loadOpcoesFilmes();
    }
    
    
    @Test
    public void should_get_new_comparison() {
        Map<Integer, Filme> comparacao = votacaoFilmeService.getNovaComparacao();
        assertComparison(comparacao);
    }
    
    
    @Test
    public void should_get_new_comparison_with_especific_movie() {
        Filme filme = filmeRepository.findById(1L);
        Map<Integer, Filme> comparacao = votacaoFilmeService.getNovaComparacaoCom(filme);
        assertComparison(comparacao);
        Assert.assertTrue(comparacao.containsValue(filme));
    }
    
    
    @Test
    public void should_generate_new_comparison_when_a_movie_has_no_more_comparisons_to_do() {
        List<Filme> filmes = filmeRepository.listFilmesOrderByVotos();
        Filme filme = filmes.get(0);
        
        for (int i = 0; i < filmes.size(); i++) {
            votacaoFilmeService.getNovaComparacaoCom(filme);
        }
        
        Map<Integer, Filme> comparacao = votacaoFilmeService.getNovaComparacaoCom(filme);
        Assert.assertNotNull(comparacao);
        
        Assert.assertFalse(comparacao.containsValue(filme));
    }
    
    
    @Test
    public void should_throw_no_more_comparison_when_all_comparison_has_been_done() {
        List<Filme> filmes = filmeRepository.listFilmesOrderByVotos();
        
        int n = filmes.size();
        
        int totalComparacoes = (n - 1);
        
        Filme filme;
        
        for (int i = totalComparacoes; i > 0; i--) {
            
            filme = filmes.get(i);
            
            for (int j = 0; j < i; j++) {
                votacaoFilmeService.getNovaComparacaoCom(filme);
            }
        }
        
        boolean exception = false;
        
        try {
            votacaoFilmeService.getNovaComparacao();
        } catch (NoMoreComparisonsException e) {
            exception = true;
        }
        
        Assert.assertTrue(exception);
    }
    
    
    @Test
    @ExpectedDatabase("classpath:/dataset/filme1_votado.xml")
    public void should_increment_vote_to_movie() {
        Filme filme = filmeRepository.findById(1L);
        votacaoFilmeService.votarEm(filme);
    }
    
    
    private void assertComparison(Map<Integer, Filme> comparacao) {
        Assert.assertNotNull(comparacao);
        Assert.assertTrue(comparacao.size() == 2);
        Assert.assertNotEquals(comparacao.get(VotacaoComponentImpl.OPCAO_1),
                comparacao.get(VotacaoComponentImpl.OPCAO_2));
    }
}
