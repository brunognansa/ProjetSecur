package com.projectSecur.model;

import org.hibernate.annotations.ColumnTransformer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name="UTILISATEUR")
public class Utilisateur extends Base {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="userId")
	private Long id;
	
	

	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name="role")
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPassword(String password) {
		 if (isValidPassword(password)) {
	            this.password = password;
	           
	     }else
	    	 throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
		 
	}
	public void encryptePassword(String password) {
		 if (isValidPassword(password)) {
	            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	            this.password = passwordEncoder.encode(password);
	            System.out.println("Mot de passe encodé : " + this.password);

		        // Vérification de la correspondance des mots de passe
	            System.out.println(passwordEncoder.matches(password, this.password));
	        } else {
	            throw new IllegalArgumentException("Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
	        }
		 
	}

	@Column(name="motDePasse")
	private String password;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPassword() {
		return password;
	}
	
	  public static boolean isValidPassword(String password) {
	        if (password == null || password.length() < 8) {
	            return false;
	        }
	        // Vérifier si le mot de passe contient au moins une lettre majuscule
	        if (!password.matches(".*[A-Z].*")) {
	            return false;
	        }
	        // Vérifier si le mot de passe contient au moins une lettre minuscule
	        if (!password.matches(".*[a-z].*")) {
	            return false;
	        }
	        // Vérifier si le mot de passe contient au moins un chiffre
	        if (!password.matches(".*\\d.*")) {
	            return false;
	        }
	        // Vérifier si le mot de passe contient au moins un caractère spécial
	        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            return false;
	        }
	        return true;
	    }

	 
	
	
	

}
