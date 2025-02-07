package com.projectSecur.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.projectSecur.model.Base;
import com.projectSecur.model.Utilisateur;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;


@Repository
public class UserDaoImpl<E,K> implements UserDao<E, K> {
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	
	@Override
	public Utilisateur getUser(String email, String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    Utilisateur user = null;
	    

        
	    //System.out.println(password);
	    
	    // Vérifie si les paramètres sont null
	    if (email == null) {
	        email = "";
	    }
	    if (password == null) {
	        password = "";
	    }
	    
	    // Récupère l'utilisateur par email
	    List<?> list = entityManager.createQuery("SELECT U FROM Utilisateur U WHERE U.email = :email")
	                                .setParameter("email", email)
	                                .getResultList();
	    
	    // Vérifie s'il y a un utilisateur correspondant à l'email
	    
	    if (!list.isEmpty()) {
	        Utilisateur retrievedUser = (Utilisateur) list.get(0);
	        System.out.println(passwordEncoder.matches(password, retrievedUser.getPassword()));
	        
	        // Compare le mot de passe haché
	        if (passwordEncoder.matches(password, retrievedUser.getPassword())) {
	            user = retrievedUser;
	            user.setError("Success");
	        }
	    }
	    
	    return user;

	}

	@Override
	public Utilisateur save(Utilisateur utilisateur) {
		return entityManager.merge(utilisateur);
	}
	//Permet de chercher un utilisateur a partir de son id dans la base de donnée et retourne soit l'utilisateur trouvé soit vide si aucun utilisateur n'a été trouvé
	@Override
	public Optional<Utilisateur> findById(Long id) {
		return Optional.ofNullable(entityManager.find(Utilisateur.class, id));
	}

	//Exécute une requete jpql pour recupérer tous les utilisateurs
	@Override
	public List<Utilisateur> findAll() {
		return (List<Utilisateur>) entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class).getResultList();
	}

	// Supprime un utilisateur par son ID s'il existe dans la base de données
	@Override
	public void deleteById(Long id) {
		Utilisateur utilisateur = entityManager.find(Utilisateur.class, id);
		if (utilisateur != null) {
			entityManager.remove(utilisateur);
		}
	}

	public Session getConnection() {
		return entityManager.unwrap(Session.class);
	}

	



	

}
