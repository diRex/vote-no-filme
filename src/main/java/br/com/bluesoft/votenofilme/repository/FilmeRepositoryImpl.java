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
	
	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#saveOrUpate(br.com.bluesoft.votenofilme.entity.Filme)
	 */
	@Override
	public Filme saveOrUpate(Filme filme) {
		return em.merge(filme);
	}

	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#listFilmes()
	 */
	@Override
	public List<Filme> listFilmes() {
		
		CriteriaQuery<Filme> criteria = em.getCriteriaBuilder().createQuery(Filme.class);
		criteria.from(Filme.class);
		
		return em.createQuery(criteria).getResultList();
	}

	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#findById(java.lang.Long)
	 */
	@Override
	public Filme findById(Long filmeId) {
		return em.find(Filme.class, filmeId);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bluesoft.votenofilme.repository.FilmeRepository#listRankingFilmes()
	 */
	public List<Filme> listRankingFilmes() {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		
		CriteriaQuery<Filme> criteria = builder.createQuery(Filme.class);
		
		Root<Filme> root = criteria.from(Filme.class);
		
		criteria.select(root).orderBy(builder.asc(root.get("totalVotos")));
		
		return em.createQuery(criteria).getResultList();
	}
}
