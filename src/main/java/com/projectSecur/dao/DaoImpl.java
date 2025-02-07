package com.projectSecur.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projectSecur.model.Base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


@Repository
public class DaoImpl<E,K> implements BaseDao<E, K> {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public E persit(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public E merge(E entity) {
		entityManager.merge(entity);
		return null;
	}

	@Transactional
	public void delete(E entity) {
		if (entity!=null)
			entityManager.remove(entity);
		
	}

	@Transactional
	public void delete(Class cl, Long id) {
		this.delete(this.find(cl, id));
		
	}

	@Override
	public E find(Class cl, Long id) {
		
		return (E)entityManager.find(cl, id);
	}

	public Session getConnection() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<E> getAll(Class cl) {
		CriteriaQuery<E> criteria = entityManager.getCriteriaBuilder().createQuery(cl);
		criteria.select(criteria.from(cl));
		List<E> resultList = entityManager.createQuery(criteria).getResultList();
		return null;
	}

	@Override
	public List<Base> findByParent(Class cl, String col, long id) {
	
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(cl);
		Root<Object> from = cq.from(cl);
		cq.select(from).where(cb.equal(from.get(col).get("id"), id));
		Query query = entityManager.createQuery(cq);
		List<Base> list = (List<Base>)query.getResultList();
		return list;
	}

}
