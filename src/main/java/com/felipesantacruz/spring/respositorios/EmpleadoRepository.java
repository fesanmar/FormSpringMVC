package com.felipesantacruz.spring.respositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.spring.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> 
{
	// Custom Query by method name
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String teleono);
}
