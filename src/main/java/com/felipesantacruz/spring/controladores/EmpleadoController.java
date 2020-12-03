package com.felipesantacruz.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.felipesantacruz.spring.modelo.Empleado;
import com.felipesantacruz.spring.servicios.EmpleadoService;
import com.felipesantacruz.spring.servicios.upload.storage.StorageService;

@Controller
public class EmpleadoController
{
	@Autowired
	private EmpleadoService servicio;

	@Autowired
	private StorageService storageService;

	@GetMapping({ "/", "empleado/list" })
	public String listado(Model modelo)
	{
		modelo.addAttribute("listaEmpleados", servicio.findAll());
		return "list";
	}

	@GetMapping("/empleado/new")
	public String nuevoEmpleadoForm(Model modelo)
	{
		modelo.addAttribute("empleadoForm", new Empleado());
		return "form";
	}

	@PostMapping("/empleado/new/submit") // NOTA: BindingResult siempre debe ir tras el parámetro anotado con @Valid
	public String nuevoEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado nuevoEmpleado,
			BindingResult bindingResult, @RequestParam("file") MultipartFile file)
	{
		// El BindingResult tiene que seguir el objeto que está vinculado. La razón es
		// que si tiene más objetos atados,
		// debe saber a qué BindingResult pertenece cada objeto.
		if (bindingResult.hasErrors())
			return "form";
		if (!file.isEmpty())
			storageEmployAvatar(nuevoEmpleado, file);
		
		servicio.add(nuevoEmpleado);
		return "redirect:/empleado/list";
	}

	private void storageEmployAvatar(Empleado nuevoEmpleado, MultipartFile file)
	{
		String avatar = storageService.store(file, nuevoEmpleado.getId());
		nuevoEmpleado.setImagen(MvcUriComponentsBuilder
				.fromMethodName(EmpleadoController.class, "serveFile", avatar).build().toUriString());
	}

	@GetMapping("/empleado/edit/{id}")
	public String editarEmpleadoForm(@PathVariable long id, Model modelo)
	{
		Empleado empleado = servicio.findById(id);
		if (empleado != null)
			modelo.addAttribute("empleadoForm", empleado);
		else
			return "redirect:/empleado/new";
		return "form";

	}

	@PostMapping("/empleado/edit/submit")
	public String editarEmpleadoSubmit(@Valid @ModelAttribute("empleadoForm") Empleado empleado,
			BindingResult bindingResult, @RequestParam("file") MultipartFile file)
	{
		if (bindingResult.hasErrors())
			return "form";
		if (!file.isEmpty())
			storageEmployAvatar(empleado, file);
		servicio.edit(empleado);
		return "redirect:/empleado/list";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename)
	{
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}
}
