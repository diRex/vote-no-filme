package br.com.bluesoft.votenofilme.component.votacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.component.votacao.model.Opcao;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Component
public class VotacaoComponentImpl implements VotacaoComponent {
    
    private List<Opcao> opcoesDisponiveis = new ArrayList<Opcao>();
    
    
    @Override
    public Comparacao getNovaComparacao() throws NoMoreComparisonsException {
        if (!this.hasOpcoesDisponiveis()) {
            throw new NoMoreComparisonsException();
        }
        
        Opcao opcao1 = this.getRandomOpcao(this.opcoesDisponiveis);
        
        return this.getNovaComparacaoCom(opcao1);
    }
    
    
    @Override
    public Comparacao getNovaComparacaoCom(final Opcao opcao1) throws NoMoreComparisonsException {
        
        List<Opcao> opcoesDisponiveis = this.getOpcoesDisponiveis(opcao1);
        
        if (opcoesDisponiveis.isEmpty()) {
            this.opcoesDisponiveis.remove(opcao1);
            throw new NoMoreComparisonsException();
        }
        
        Opcao opcao2 = this.getRandomOpcao(opcoesDisponiveis);
        Comparacao comparacao = new Comparacao(opcao1, opcao2);
        
        this.filtrarOpcoesDisponiveis(opcao1, opcao2);
        
        return comparacao;
    }
    
    
    @Override
    public void setOpcoesDisponiveis(List<Opcao> opcoes) {
        this.opcoesDisponiveis.addAll(opcoes);
    }
    
    
    /**
     * Verifica se ainda existem opções disponiveis para fazer uma comparacao
     * 
     * @return true quando houver opções disponiveis
     */
    private boolean hasOpcoesDisponiveis() {
        return this.opcoesDisponiveis.size() >= 2;
    }
    
    
    /**
     * Obtém uma opção disponível randomica
     * 
     * @return opcao
     */
    private Opcao getRandomOpcao(List<Opcao> opcoesDisponiveis) {
        int max = opcoesDisponiveis.size();
        int index = (int) (Math.random() * max);
        return this.opcoesDisponiveis.get(index);
    }
    
    
    /**
     * Filtra as opcoes que ja foram comparadas
     * 
     * @param opcoes
     */
    private void filtrarOpcoesDisponiveis(final Opcao... opcoes) {
        for (Opcao opcao : opcoes) {
            if (this.getOpcoesDisponiveis(opcao).isEmpty()) {
                this.opcoesDisponiveis.remove(opcao);
            }
        }
    }
    
    
    /**
     * Obtém as opções disponíveis para comparar com a opção passada por parametro
     * 
     * @param opcao
     * @return lista de opções disponiveis
     */
    private List<Opcao> getOpcoesDisponiveis(final Opcao opcao) {
        List<Opcao> opcoesDisponiveis = Lists.newArrayList(Collections2.filter(this.opcoesDisponiveis,
                new Predicate<Opcao>() {
                    
                    @Override
                    public boolean apply(Opcao op) {
                        return (!opcao.getComparados().contains(op));
                    }
                }));
        return opcoesDisponiveis;
    }
    
    public class Comparacao {
        
        private Opcao opcao1;
        
        private Opcao opcao2;
        
        
        public Comparacao(final Opcao opcao1, final Opcao opcao2) {
            
            this.opcao1 = opcao1;
            this.opcao2 = opcao2;
            
            opcao1.setComparadoCom(opcao2);
            opcao2.setComparadoCom(opcao1);
        }
        
        
        public Opcao getOpcao1() {
            return this.opcao1;
        }
        
        
        public void setOpcao1(final Opcao opcao1) {
            this.opcao1 = opcao1;
        }
        
        
        public Opcao getOpcao2() {
            return this.opcao2;
        }
        
        
        public void setOpcao2(final Opcao opcao2) {
            this.opcao2 = opcao2;
        }
        
    }
}
