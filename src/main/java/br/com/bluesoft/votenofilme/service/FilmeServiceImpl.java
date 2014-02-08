package br.com.bluesoft.votenofilme.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponent;
import br.com.bluesoft.votenofilme.component.votacao.VotacaoComponentImpl.Comparacao;
import br.com.bluesoft.votenofilme.component.votacao.model.Opcao;
import br.com.bluesoft.votenofilme.model.Filme;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
@Transactional
public class FilmeServiceImpl implements FilmeService {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private VotacaoComponent votacaoComponent;
    
    
    @Override
    public void loadOpcoes() {
        
        List<Filme> filmes = this.listRankingFilmes();
        
        List<Opcao> opcoes = Lists.transform(filmes, new Function<Filme, Opcao>() {
            
            @Override
            public Opcao apply(Filme filme) {
                return null;
            }
        });
        
        this.votacaoComponent.setOpcoes(opcoes);
    }
    
    
    @Override
    public void votarEm(final long filmeId) {
        Filme filme = this.findById(filmeId);
        filme.incrementarVotosEm(1);
        this.em.merge(filme);
    }
    
    
    @Override
    public Filme findById(final long filmeId) {
        return this.em.find(Filme.class, filmeId);
    }
    
    
    @Override
    public List<Filme> listRankingFilmes() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        
        CriteriaQuery<Filme> criteria = builder.createQuery(Filme.class);
        
        Root<Filme> root = criteria.from(Filme.class);
        
        criteria.select(root).orderBy(builder.desc(root.get("totalVotos")), builder.asc(root.get("nome")));
        
        return this.em.createQuery(criteria).getResultList();
    }
    
    
    @Override
    public Comparacao obterComparacao() {
        return this.obterComparacao();
    }
    
    
    @Override
    public Comparacao obterComparacaoCom(final Long filmeId) {
        Filme filme = this.findById(filmeId);
        return this.votacaoComponent.getNovaComparacaoCom(new Opcao(filme.getId(), filme.getNome()));
    }
    
}
