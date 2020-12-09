package com.felipesantacruz.spring;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.felipesantacruz.spring.modelo.Empleado;
import com.felipesantacruz.spring.respositorios.EmpleadoRepository;
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
	
	@Bean
	CommandLineRunner initData(EmpleadoRepository repository)
	{
		return args -> {
			repository.saveAll(
					Arrays.asList(new Empleado(1,"Antonio García", "antonio.garcia@openwebinars.net", "954000000", true),
							new Empleado(2,"María López", "maria.lopez@openwebinars.net", "954000000", false),
							new Empleado(3,"Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000", false))
					);
		};
	}

}
