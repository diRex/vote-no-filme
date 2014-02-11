package br.com.bluesoft.votenofilme.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
import br.com.bluesoft.votenofilme.component.votacao.exception.NoMoreComparisonsException;
import br.com.bluesoft.votenofilme.model.Filme;
import br.com.bluesoft.votenofilme.repository.FilmeRepository;

@Service
@Transactional
public class VotacaoFilmeServiceImpl implements VotacaoFilmeService {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @Autowired
    private VotacaoComponent<Filme> votacaoComponent;
    
    
    @Override
    public void votarEm(final Filme filme) {
        filme.incrementarVotosEm(1);
        filmeRepository.saveOrUpdate(filme);
    }
    
    
    @Override
    public List<Filme> listRankingFilmes() {
        return filmeRepository.listFilmesOrderByVotos();
    }
    
    
    @Override
    public void loadOpcoesFilmes() {
        List<Filme> filmes = filmeRepository.listFilmesOrderByVotos();
        votacaoComponent.setOpcoes(filmes);
    }
    
    
    @Override
    public Map<Integer, Filme> getNovaComparacaoCom(Filme filme) {
        Map<Integer, Filme> comparacao = null;
        
        try {
            comparacao = votacaoComponent.getNovaComparacaoCom(filme);
        } catch (NoMoreComparisonsException e) {
            comparacao = votacaoComponent.getNovaComparacao();
        }
        return comparacao;
        
    }
    
    
    @Override
    public Map<Integer, Filme> getNovaComparacao() {
        return votacaoComponent.getNovaComparacao();
    }
}
