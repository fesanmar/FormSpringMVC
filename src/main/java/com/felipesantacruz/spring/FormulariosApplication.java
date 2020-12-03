package com.felipesantacruz.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.felipesantacruz.spring.servicios.upload.storage.StorageService;

@SpringBootApplication
public class FormulariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormulariosApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService)
	{
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

}
