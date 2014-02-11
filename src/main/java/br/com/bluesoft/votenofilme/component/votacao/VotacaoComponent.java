package br.com.bluesoft.votenofilme.component.votacao;

import java.util.List;
import java.util.Map;

import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;

public interface VotacaoComponent<E extends Opcao> {
    
    public static final int OPCAO_1 = 1;
    
    public static final int OPCAO_2 = 2;
    
    
    /**
     * Obtem nova comparacao para uma opcao
     * 
     * @param opcao1 opcao a ser comparada
     * @return map com indece da opcao e opcao
     * @throws NoMoreComparisonsException
     */
    public Map<Integer, E> getNovaComparacaoCom(final E opcao1) throws NoMoreComparisonsException;
    
    
    /**
     * Obtem nova comparacao entre duas opcoes aletorias
     * 
     * @return map com indece da opcao e opcao
     * @throws NoMoreComparisonsException
     */
    public Map<Integer, E> getNovaComparacao() throws NoMoreComparisonsException;
    
    
    /**
     * Adiciona opcoes possiveis para voto
     * 
     * @param opcoes
     */
    public void setOpcoes(List<E> opcoes);
    
}
