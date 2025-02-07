package com.projectSecur.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectSecur.dao.BaseDao;
import com.projectSecur.dao.UserDao;
import com.projectSecur.model.Base;
import com.projectSecur.model.Utilisateur;

import jakarta.transaction.Transactional;

@Service(value="UserService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
		
	BaseService baseService;
	@Transactional
	@Override
	public Base save(Utilisateur entity) {
		Utilisateur user = entity;
		user.encryptePassword(entity.getPassword());
		
		
		String error= "erreur";
		try {
				user = (Utilisateur)this.baseService.save(user);
				if (user==null)
					error="Erreur lors de l'enregistrement de l'utilisateur";
			
			
		}catch(Exception e) {
			e.printStackTrace();
			user.setError(error);

		}
		
		return user;
	}
	
	
	@Transactional
	@Override
	public Utilisateur getUser(String email, String password) {
		
		Utilisateur user = null;
		String error = "echec";
				
				
		try {
			user = (Utilisateur)this.userDao.getUser(email,password);
			if (user==null)
				user= new Utilisateur();
				error="Utilisateur ou mot de passe invalide";
		
		
	}catch(Exception e) {
		e.printStackTrace();
		user.setError(error);

	}
	
	return user;
}


}
