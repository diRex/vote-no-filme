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
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class VotacaoComponent <E extends Opcao> {
    
    public static final int OPCAO_1 = 1;
    public static final int OPCAO_2 = 2;
    
    private Map<E, List<E>> opcaoComparacoes = new HashMap<E, List<E>>();
    
    /**
     * Obtem nova comparacao para uma opcao
     * 
     * @param opcao1 opcao a ser comparada
     * @param opcoes opcoes diponiveis para comparacao
     * @return comparacao
     * @throws NoMoreComparisonsException
     */
    public Map<Integer, E> getNovaComparacaoCom(final E opcao1, List<E> opcoes)
            throws NoMoreComparisonsException {
        
        if (opcoes.isEmpty()) {
            throw new NoMoreComparisonsException();
        }
        
        opcoes = this.getOpcoesDisponiveis(opcao1, opcoes);
        
        E opcao2 = this.getRandomOpcao(opcoes);
        
        return this.gerarComparacao(opcao1, opcao2);
    }
    
    /**
     * Obtem uma opcao randomica
     * 
     * @return opcao
     */
    public E getRandomOpcao(List<E> opcoes) {
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
        
        setComparados(opcao1, opcao2);
        setComparados(opcao2, opcao1);
        
        return comparacao;
    }
    
    /**
     * Salva as comparacoes realizadas entre as opcoes
     * 
     * @param opcao1
     * @param opcao2
     */
    private void setComparados(E opcao1, E opcao2) {
        List<E> opcoesComparadas = getOpcoesComparadas(opcao1);
        
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
    private List<E> getOpcoesDisponiveis(final E opcao, List<E> opcoes) {
       
        final List<E> opcoesComparadas = getOpcoesComparadas(opcao);
        
        List<E> opcoesDisponiveis = Lists.newArrayList(Collections2.filter(opcoes, new Predicate<E>() {
            
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
        return (opcoesComparadas == null ) ? new ArrayList<E>() : opcoesComparadas;
    }

}
