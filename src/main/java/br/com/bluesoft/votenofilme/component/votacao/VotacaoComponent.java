package br.com.bluesoft.votenofilme.component.votacao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Component
public class VotacaoComponent {
    
    public static final int OPCAO_1 = 1;
    public static final int OPCAO_2 = 2;
    
    
    /**
     * Obtem nova comparacao para uma opcao
     * 
     * @param opcao1 opcao a ser comparada
     * @param opcoes opcoes diponiveis para comparacao
     * @return comparacao
     * @throws NoMoreComparisonsException
     */
    public Map<Integer, ? extends Opcao> getNovaComparacaoCom(final Opcao opcao1, List<? extends Opcao> opcoes)
            throws NoMoreComparisonsException {
        
        if (opcoes.isEmpty()) {
            throw new NoMoreComparisonsException();
        }
        
        opcoes = this.getOpcoesDisponiveis(opcao1, opcoes);
        
        Opcao opcao2 = this.getRandomOpcao(opcoes);
        
        Map<Integer, Opcao> comparacao = new HashMap<Integer, Opcao>();
        comparacao.put(OPCAO_1, opcao1);
        comparacao.put(OPCAO_2, opcao2);
        
        return comparacao;
    }
    
    
    /**
     * Obtem uma lista de opcoes disponiveis para compara com outra opcao
     * 
     * @param opcao
     * @param opcoes
     * @return
     */
    private List<? extends Opcao> getOpcoesDisponiveis(final Opcao opcao, List<? extends Opcao> opcoes) {
        return Lists.newArrayList(Collections2.filter(opcoes, new Predicate<Opcao>() {
            
            @Override
            public boolean apply(Opcao op) {
                return !op.equals(opcao);
            }
        }));
    }
    
    
    /**
     * Obtem uma opcao randomica
     * 
     * @return opcao
     */
    public Opcao getRandomOpcao(List<? extends Opcao> opcoes) {
        int max = opcoes.size();
        int index = (int) (Math.random() * max);
        return opcoes.get(index);
    }
    
}
