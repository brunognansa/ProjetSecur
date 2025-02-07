package com.projectSecur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@EntityScan(basePackages = "com.projectSecur.model")
public class ProjectSecurApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSecurApplication.class, args);
	}
	
	


}
