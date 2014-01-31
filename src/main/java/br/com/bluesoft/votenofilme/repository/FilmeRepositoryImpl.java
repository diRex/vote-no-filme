package br.com.bluesoft.votenofilme.repository;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import br.com.bluesoft.votenofilme.entity.Filme;


@Repository
public class FilmeRepositoryImpl implements FilmeRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    /*
     * (non-Javadoc)
     * 
     * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#saveOrUpate(br.com.bluesoft.votenofilme.entity.Filme)
     */
    @Override
    public Filme saveOrUpate(Filme filme) {
    
        return this.em.merge(filme);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#listFilmes()
     */
    @Override
    public List<Filme> listFilmes() {
    
        CriteriaQuery<Filme> criteria = this.em.getCriteriaBuilder().createQuery(Filme.class);
        criteria.from(Filme.class);
        
        return this.em.createQuery(criteria).getResultList();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#findById(java.lang.Long)
     */
    @Override
    public Filme findById(Long filmeId) {
    
        return this.em.find(Filme.class, filmeId);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#listRankingFilmes()
     */
    @Override
    public List<Filme> listRankingFilmes() {
    
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        
        CriteriaQuery<Filme> criteria = builder.createQuery(Filme.class);
        
        Root<Filme> root = criteria.from(Filme.class);
        
        criteria.select(root).orderBy(builder.desc(root.get("totalVotos")), builder.asc(root.get("nome")));
        
        return this.em.createQuery(criteria).getResultList();
    }
}
