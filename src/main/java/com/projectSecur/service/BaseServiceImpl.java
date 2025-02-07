package com.projectSecur.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectSecur.dao.BaseDao;
import com.projectSecur.model.Base;

@Service(value="BaseService")
public class BaseServiceImpl implements BaseService {
	@Autowired
	
	BaseDao<Base,String> baseDao;
	
	@Override
	public Base save(Base entity) {
		Base saveBase= entity;
		try {
			entity.setCreateDate(new Date());
			if(entity.getId()==null) {
				saveBase = this.baseDao.persit(entity);
			}else
				saveBase = this.baseDao.merge(entity);
			saveBase.setError("Success");
		}catch(Exception e) {
			e.printStackTrace();
			saveBase.setError(e.getMessage());

		}
		return saveBase;
	}

	@Override
	public void delete(Base entity) {
		baseDao.delete(entity);
		
	}

	@Override
	public void delete(Class cl, Long id) {
		this.baseDao.delete(cl, id);
		
	}

	@Override
	public Base find(Class cl, Long id) {
		// TODO Auto-generated method stub
		return this.baseDao.find(cl, id);
	}

	@Override
	public Session getConnection() {
		return this.baseDao.getConnection();
	}

	@Override
	public List<Base> getAll(Class cl) {
		return baseDao.getAll(cl);
	}

	@Override
	public List<Base> findByParent(Class cl, String col, long id) {
		return this.baseDao.findByParent(cl, col, id);
	}

}
