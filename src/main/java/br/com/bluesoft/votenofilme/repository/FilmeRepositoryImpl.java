package br.com.bluesoft.votenofilme.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import br.com.bluesoft.votenofilme.entity.Filme;

@Repository
public class FilmeRepositoryImpl extends AbstractRepository implements FilmeRepository {

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
}
