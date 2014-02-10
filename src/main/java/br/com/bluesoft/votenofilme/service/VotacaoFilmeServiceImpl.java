package br.com.bluesoft.votenofilme.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
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
        this.filmeRepository.saveOrUpdate(filme);
    }

    @Override
    public List<Filme> listRankingFilmes() {
        return this.filmeRepository.listFilmesOrderByVotos();
    }

    @Override
    public void loadOpcoesFilmes() {
        List<Filme> filmes = this.filmeRepository.listFilmesOrderByVotos();
        this.votacaoComponent.setOpcoes(filmes);
    }

    @Override
    public Map<Integer, Filme> getNovaComparacaoCom(Filme filme) {
        return this.votacaoComponent.getNovaComparacaoCom(filme);
        
    }

    @Override
    public Map<Integer, Filme> getNovaComparacao() {
        return this.votacaoComponent.getNovaComparacaoCom();
    }
}
