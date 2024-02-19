package com.freelance.pfe;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.freelance.pfe.security.services.FilesStorageService;

@SpringBootApplication
public class SpringBootJwtAuthenticationExamplesApplication implements CommandLineRunner {

	@Resource
	  FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtAuthenticationExamplesApplication.class, args);
	}
	
	 public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }
}
