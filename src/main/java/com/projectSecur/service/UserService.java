package com.projectSecur.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.projectSecur.model.Base;
import com.projectSecur.model.Utilisateur;

@Service(value="UserService")
public interface UserService {
	
	public Base save(Utilisateur entity);
	public Utilisateur getUser(String email, String password);


}
