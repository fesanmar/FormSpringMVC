package com.felipesantacruz.spring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
			List<String> nombres = Arrays.asList("Lucas", "Hugo", "Martín", "Daniel", "Pablo", "Alejandro", "Mateo",
					"Adrián", "Álvaro", "Manuel", "Leo", "David", "Mario", "Diego", "Javier", "Luis", "Marcos", "Juan",
					"José", "Gonzalo", "Lucía", "Sofía", "María", "Martina", "Paula", "Julia", "Daniela", "Valeria",
					"Alba", "Emma", "Carla", "Sara", "Noa", "Carmen", "Claudia", "Valentina", "Alma", "Ana", "Luisa",
					"Marta");

			List<String> apellidos = Arrays.asList("García", "González", "López", "Rodríguez", "Martínez", "Sánchez",
					"Pérez", "Gómez", "Martín", "Saez", "Velasco", "Moya", "Soler", "Parra", "Bravo", "Rojas", "Romero",
					"Sosa", "Torres", "Álvarez", "Flores", "Molina", "Ortiz", "Silva", "Torres");


			
			Collections.shuffle(nombres);
			
			Random random = new Random();
			
			repository.saveAll(IntStream.rangeClosed(1, nombres.size()).mapToObj((i) -> {
				String nombre = nombres.get(i-1);
				String apellido1 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				String apellido2 = apellidos.get(ThreadLocalRandom.current().nextInt(apellidos.size()));
				return new Empleado(String.format("%s %s %s", nombre, apellido1, apellido2), 
						String.format("%s.%s@openwebinars.net", nombre.toLowerCase(), apellido1.toLowerCase()), "954000000", random.nextBoolean());
			}).collect(Collectors.toList()));

		};
	}

}
