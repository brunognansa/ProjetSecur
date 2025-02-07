package com.projectSecur.dao;

import java.util.List;

import org.hibernate.Session;

import com.projectSecur.model.Base;

public interface BaseDao<E,K> {
	
	public E persit(E entity);
	public E merge(E entity);
	public List<E> getAll(Class cl);
	public void delete(E entity);
	public void delete(Class cl,Long id);
	public E find(Class cl,Long id);
	public List<Base> findByParent(Class cl, String col, long id);
	public Session getConnection();
	

}
