package br.com.bluesoft.votenofilme.test.component.votacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.test.BaseTest;

public class VotacaoComponentTest extends BaseTest {
    
    @Autowired
    private VotacaoComponent<Filme> votacaoComponent;
    private List<Filme> filmes;
    
    
    @Before
    public void setup() {
        this.filmes = new ArrayList<Filme>();
        
        this.filmes.add(new Filme(1L, "Harry Potter e a Pedra Filosofal", "harry_potter_01.jpg", 0L));
        this.filmes.add(new Filme(2L, "O Poderoso Chefão", "poderoso_chefao.jpg", 0L));
        this.filmes.add(new Filme(3L, "O Senhor dos Anéis - O Retorno do Rei", "senhor_dos_aneis_03.jpg", 0L));
        this.filmes.add(new Filme(4L, "Toy Story", "toy_story_01.jpg", 0L));
        this.filmes.add(new Filme(5L, "Iron Man 3", "iron_man_03.jpg", 0L));
    }
    
    
    @Test
    public void should_generate_new_comparison() {
        Filme filme = this.filmes.get(0);
        Map<Integer, Filme> comparacao = (Map<Integer, Filme>) this.votacaoComponent.getNovaComparacaoCom(filme,
                this.filmes);
        
        Assert.assertNotNull(comparacao);
        Assert.assertTrue(comparacao.entrySet().size() == 2);
        Assert.assertNotEquals(comparacao.get(VotacaoComponent.OPCAO_1), comparacao.get(VotacaoComponent.OPCAO_2));
    }
    
    
    @Test(expected = NoMoreComparisonsException.class)
    public void should_throws_no_more_comparison_exception() {
        Filme filme = this.filmes.get(1);
        for (int i = 0; i < this.filmes.size(); i++) {
            this.votacaoComponent.getNovaComparacaoCom(filme, this.filmes);
        }
    }
    
}
