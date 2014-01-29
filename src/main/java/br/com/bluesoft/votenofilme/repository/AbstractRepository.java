package br.com.bluesoft.votenofilme.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository {
	
	@PersistenceContext
	EntityManager em;
	
}
