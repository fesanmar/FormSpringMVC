package com.felipesantacruz.spring.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.felipesantacruz.spring.modelo.Empleado;

@Service("empleadoServiceInMemory")
public class EmpleadoServiceInMemory implements EmpleadoService
{
	private List<Empleado> repositorio = new ArrayList<>();

	public Empleado add(Empleado e)
	{
		repositorio.add(e);
		return e;
	}

	public List<Empleado> findAll()
	{
		return repositorio;
	}

	public Empleado findById(long id)
	{
		Empleado result = null;
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorio.size())
		{
			if (repositorio.get(i).getId() == id)
			{
				encontrado = true;
				result = repositorio.get(i);
			} else
			{
				i++;
			}
		}
		return result;
	}

	public Empleado edit(Empleado e)
	{
		boolean encontrado = false;
		int i = 0;
		while (!encontrado && i < repositorio.size())
		{
			if (repositorio.get(i).getId() == e.getId())
			{
				encontrado = true;
				repositorio.remove(i);
				repositorio.add(i, e);
			} else
			{
				i++;
			}
		}
		if (!encontrado)
			repositorio.add(e);
		return e;

	}

	@Override
	public List<Empleado> findByAnyMatch(String str)
	{
		return findAll()
				.stream()
				.filter(empl -> anyFieldMatchesPattern(empl, str))
				.collect(Collectors.toList());
	}

	public boolean anyFieldMatchesPattern(Empleado empl, String str)
	{
		return containsIgnoreCase(empl.getNombre(), str) || containsIgnoreCase(empl.getEmail(), str)
				|| containsIgnoreCase(empl.getTelefono(), str);
	}

	public boolean containsIgnoreCase(String field, String patter)
	{
		return field.toLowerCase().contains(patter.toLowerCase());
	}

	@PostConstruct
	public void init()
	{
		repositorio.addAll(
				Arrays.asList(new Empleado(1, "Antonio García", "antonio.garcia@openwebinars.net", "954000000", true),
						new Empleado(2, "María López", "maria.lopez@openwebinars.net", "954000000", false),
						new Empleado(3, "Ángel Antúnez", "angel.antunez@openwebinars.net", "954000000", false)));
	}
}
