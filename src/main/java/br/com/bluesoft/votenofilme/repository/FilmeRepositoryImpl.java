package br.com.bluesoft.votenofilme.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.votenofilme.model.Filme;

@Repository
public class FilmeRepositoryImpl implements FilmeRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Filme findById(final long filmeId) {
        return this.em.find(Filme.class, filmeId);
    }
    
    @Override
    public List<Filme> listFilmesOrderByVotos() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        
        CriteriaQuery<Filme> criteria = builder.createQuery(Filme.class);
        
        Root<Filme> root = criteria.from(Filme.class);
        
        criteria.select(root).orderBy(builder.desc(root.get("totalVotos")), builder.asc(root.get("nome")));
        
        return this.em.createQuery(criteria).getResultList();
    }

    @Override
    public Filme saveOrUpdate(Filme filme) {
        return this.em.merge(filme);
    }
}
