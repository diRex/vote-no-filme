package br.com.bluesoft.votenofilme.component.votacao;

import java.util.List;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl.Comparacao;
import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.component.votacao.model.Opcao;

public interface VotacaoComponent {
    
    /**
     * Obtém uma nova comparacao entre duas opções que ainda não foram comparadas
     * 
     * @return comparacao
     * @throws NoMoreComparisonsException quando não houver mais comparações possíveis
     */
    Comparacao getNovaComparacao() throws NoMoreComparisonsException;
    
    
    /**
     * Obtém uma nova comparacao entre a opção passada como parametro e outra opção que ainda não foi comparada
     * 
     * @param opcao - opção a ser comparada com outra
     * @return comparacao
     * @throws NoMoreComparisonsException quando não houver mais comparações possíveis
     */
    Comparacao getNovaComparacaoCom(Opcao opcao) throws NoMoreComparisonsException;
    
    
    /**
     * Adiciona lista de opções disponíveis
     * 
     * @param opcoes - opçoes disponiveis
     */
    void setOpcoesDisponiveis(List<Opcao> opcoes);
}
