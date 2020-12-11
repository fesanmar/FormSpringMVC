package com.felipesantacruz.spring.servicios;

import java.util.List;

import com.felipesantacruz.spring.modelo.Empleado;

public interface EmpleadoService
{
	public Empleado add(Empleado e);
	
	public List<Empleado> findAll();
	
	public Empleado findById(long id);
	
	public Empleado edit(Empleado e);

	public List<Empleado> findByAnyMatch(String str);

	public List<Empleado> findByAnyMatchAndIsDirectivoEqualTo(String str, boolean isDirectivo);
}
