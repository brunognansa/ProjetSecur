package com.projectSecur.controller;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectSecur.model.Base;

import com.projectSecur.model.Utilisateur;
import com.projectSecur.service.BaseServiceImpl;
import com.projectSecur.service.UserServiceImpl;

import jakarta.persistence.criteria.Path;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value="service/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	@Qualifier("BaseService")
	BaseServiceImpl baseService;
	
	@Autowired
	@Qualifier("UserService")
	UserServiceImpl userService;
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public String Salutation(@RequestBody Utilisateur user) {
		return new String("Salutation cher ami je suis heureux sa marche");
	}
	
	@RequestMapping(value="/login",method= RequestMethod.POST)
	@Transactional
	public Base login(@RequestBody Utilisateur user) {
		
		System.out.println(user.getPassword());

		return this.userService.getUser(user.getEmail(),user.getPassword());
		
	}
	
	@RequestMapping(value="/register",method= RequestMethod.POST)
	@Transactional
	public Base register(@RequestBody Utilisateur user) {
		Base savedUser = null;
		try {
		
		savedUser = this.userService.save(user);
		}catch(Exception e) {
			if (savedUser==null)
				savedUser= new Utilisateur();
				savedUser.setError(e.getMessage());
		}
		return savedUser;
	}

	
	
	
	
	
	
	
	

}
