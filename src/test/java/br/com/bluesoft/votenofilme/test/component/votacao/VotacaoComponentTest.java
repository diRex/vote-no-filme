package br.com.bluesoft.votenofilme.test.component.votacao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl.Comparacao;
import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.component.votacao.model.Opcao;
import br.com.bluesoft.votenofilme.test.BaseTest;

public class VotacaoComponentTest extends BaseTest {
    
    @Autowired
    private VotacaoComponent votacaoComponent;
    
    private final Opcao harryPotter = new Opcao(1L, "Harry Potter e a Pedra Filosofal");;
    private final Opcao senhorDosAneis = new Opcao(2L, "O Senhor do Aneis - O Retorno do Rei");;
    private final Opcao poderosoChefao = new Opcao(3L, "Poderoso Chefão");
    private final Opcao toyStore = new Opcao(4L, "ToyStore");
    private final Opcao matrix = new Opcao(5L, "Matrix");
    
    
    @Before
    public void setup() {
        
        List<Opcao> opcoesDisponiveis = new ArrayList<Opcao>();
        opcoesDisponiveis.add(this.harryPotter);
        opcoesDisponiveis.add(this.senhorDosAneis);
        opcoesDisponiveis.add(this.poderosoChefao);
        opcoesDisponiveis.add(this.toyStore);
        opcoesDisponiveis.add(this.matrix);
        
        this.votacaoComponent.setOpcoesDisponiveis(opcoesDisponiveis);
    }
    
    
    @Test
    public void should_generate_new_comparison() {
        Comparacao comparacao = this.votacaoComponent.getNovaComparacao();
        this.assertComparison(comparacao);
    }
    
    
    @Test
    public void should_generate_new_comparison_with_a_defined_option() {
        Comparacao comparacao = this.votacaoComponent.getNovaComparacaoCom(this.senhorDosAneis);
        this.assertComparison(comparacao);
        Assert.assertEquals(this.senhorDosAneis, comparacao.getOpcao1());
    }
    
    
    @Test(expected = NoMoreComparisonsException.class)
    public void should_throws_no_more_comparison_exception() {
        this.harryPotter.setComparadoCom(this.senhorDosAneis);
        this.harryPotter.setComparadoCom(this.poderosoChefao);
        this.harryPotter.setComparadoCom(this.toyStore);
        this.harryPotter.setComparadoCom(this.matrix);
        this.votacaoComponent.getNovaComparacaoCom(this.harryPotter);
    }
    
    
    /**
     * Assert default para comparação
     * 
     * @param comparacao
     */
    private void assertComparison(Comparacao comparacao) {
        Assert.assertNotNull(comparacao);
        Assert.assertNotNull(comparacao.getOpcao1());
        Assert.assertNotNull(comparacao.getOpcao2());
        Assert.assertNotEquals(comparacao.getOpcao1(), comparacao.getOpcao2());
        // Assert.assertFalse(comparacao.getOpcao1().getComparados().contains(comparacao.getOpcao2()));
        // Assert.assertFalse(comparacao.getOpcao2().getComparados().contains(comparacao.getOpcao1()));
        
    }
    
}
