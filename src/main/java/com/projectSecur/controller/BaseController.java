package com.projectSecur.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectSecur.model.Base;

import com.projectSecur.service.BaseServiceImpl;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping(value="service/base")
@CrossOrigin(origins="*",maxAge=3600)
public class BaseController {
	
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String Salutation() {
		return new String("Salutation cher ami je debute en spring boot");
	}


}
