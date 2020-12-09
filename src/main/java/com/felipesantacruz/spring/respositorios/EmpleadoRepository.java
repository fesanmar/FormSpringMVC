package com.felipesantacruz.spring.respositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipesantacruz.spring.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> 
{

}
