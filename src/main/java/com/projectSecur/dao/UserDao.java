package com.projectSecur.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;

import com.projectSecur.model.Base;
import com.projectSecur.model.Utilisateur;

public interface UserDao<E,K> {
	
	public Utilisateur getUser(String email, String password);

	Utilisateur save(Utilisateur utilisateur);
	Optional<Utilisateur> findById(Long id);
	List<Utilisateur> findAll();
	void deleteById(Long id);


}
