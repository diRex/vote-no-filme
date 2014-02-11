package br.com.bluesoft.votenofilme.component.votacao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class VotacaoComponentImpl<E extends Opcao> implements VotacaoComponent<E> {
    
    public static final int OPCAO_1 = 1;
    public static final int OPCAO_2 = 2;
    
    private Map<E, List<E>> opcaoComparacoes = new HashMap<E, List<E>>();
    private List<E> opcoes = new ArrayList<E>();
    
    
    @Override
    public Map<Integer, E> getNovaComparacaoCom(final E opcao1) {
        
        if (this.opcoes.isEmpty()) {
            throw new NoMoreComparisonsException();
        }
        
        List<E> opcoesDisponiveis = this.getOpcoesDisponiveis(opcao1);
        
        if (opcoesDisponiveis.isEmpty()) {
            throw new NoMoreComparisonsException();
        }
        
        E opcao2 = this.getRandomOpcao(opcoesDisponiveis);
        
        return this.gerarComparacao(opcao1, opcao2);
    }
    
    
    @Override
    public Map<Integer, E> getNovaComparacao() {
        if (!this.hasOpcoesDisponiveis()) {
            throw new NoMoreComparisonsException();
        }
        E opcao = this.getRandomOpcao(this.opcoes);
        
        return this.getNovaComparacaoCom(opcao);
    }
    
    
    @Override
    public void setOpcoes(List<E> opcoes) {
        this.opcoes = opcoes;
        this.opcaoComparacoes = new HashMap<E, List<E>>();
    }
    
    
    /**
     * Obtem uma opcao randomica
     * 
     * @return opcao
     */
    private E getRandomOpcao(List<E> opcoes) {
        int max = opcoes.size();
        int index = (int) (Math.random() * max);
        return opcoes.get(index);
    }
    
    
    /**
     * Geraca comparacao entre duas opcoes
     * 
     * @param opcao1
     * @param opcao2
     * @return Map com o indece da opcao e a opcao
     */
    private Map<Integer, E> gerarComparacao(E opcao1, E opcao2) {
        Map<Integer, E> comparacao = new HashMap<Integer, E>();
        comparacao.put(OPCAO_1, opcao1);
        comparacao.put(OPCAO_2, opcao2);
        
        this.setComparados(opcao1, opcao2);
        this.setComparados(opcao2, opcao1);
        
        this.filtrarOpcoes();
        
        return comparacao;
    }
    
    
    private void filtrarOpcoes() {
        this.opcoes = Lists.newArrayList(Collections2.filter(this.opcoes, new Predicate<E>() {
            
            @Override
            public boolean apply(final E opcao) {
                return !getOpcoesDisponiveis(opcao).isEmpty();
            }
            
        }));
    }
    
    
    /**
     * Salva as comparacoes realizadas entre as opcoes
     * 
     * @param opcao1
     * @param opcao2
     */
    private void setComparados(E opcao1, E opcao2) {
        List<E> opcoesComparadas = this.getOpcoesComparadas(opcao1);
        
        opcoesComparadas.add(opcao2);
        
        this.opcaoComparacoes.put(opcao1, opcoesComparadas);
    }
    
    
    /**
     * Obtem uma lista de opcoes disponiveis para compara com outra opcao
     * 
     * @param opcao
     * @param opcoes
     * @return
     */
    private List<E> getOpcoesDisponiveis(final E opcao) {
        
        final List<E> opcoesComparadas = this.getOpcoesComparadas(opcao);
        
        List<E> opcoesDisponiveis = Lists.newArrayList(Collections2.filter(this.opcoes, new Predicate<E>() {
            
            @Override
            public boolean apply(E op) {
                return !opcoesComparadas.contains(op) && !op.equals(opcao);
            }
        }));
        
        return opcoesDisponiveis;
    }
    
    
    /**
     * Obtem opcoes comparadas com uma opcao
     * 
     * @param opcao
     * @return lista de opcoes comparadas, caso ainda nao tenha nenhuma comparacao, retorna um ArrayList vazio
     */
    private List<E> getOpcoesComparadas(E opcao) {
        List<E> opcoesComparadas = this.opcaoComparacoes.get(opcao);
        return (opcoesComparadas == null) ? new ArrayList<E>() : opcoesComparadas;
    }
    
    
    /**
     * Verifica se ainda tem opcoes disponiveis para comparacao
     * 
     * @return true quando houver
     */
    private boolean hasOpcoesDisponiveis() {
        return this.opcoes.size() >= 2;
    }
    
}
