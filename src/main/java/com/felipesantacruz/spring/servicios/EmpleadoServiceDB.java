package com.felipesantacruz.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.felipesantacruz.spring.modelo.Empleado;
import com.felipesantacruz.spring.respositorios.EmpleadoRepository;

@Primary
@Service("empleadoServiceDB")
public class EmpleadoServiceDB implements EmpleadoService
{
	@Autowired
	private EmpleadoRepository repository;
	
	@Override
	public Empleado add(Empleado e)
	{
		return repository.save(e);
	}

	@Override
	public List<Empleado> findAll()
	{
		return repository.findAll();
	}

	@Override
	public Empleado findById(long id)
	{
		return repository.findById(id).orElse(null);
	}

	@Override
	public Empleado edit(Empleado e)
	{
		return repository.save(e);
	}

}