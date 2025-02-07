package com.projectSecur.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.projectSecur.model.Base;

@Service(value="BaseService")
public interface BaseService {
	
	public Base save(Base entity);
	public void delete(Base entity);
	public void delete(Class cl,Long id);
	public List<Base> getAll(Class cl);
	public Base find(Class cl,Long id);
	public List<Base> findByParent(Class cl, String col, long id);
	public Session getConnection();

}
