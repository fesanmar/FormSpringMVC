package com.felipesantacruz.spring.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Empleado
{
	@Id
	@GeneratedValue
//	@Min(value = 1, message = "{empleado.id.mayorquecero}") comentado porque se generará automáticamente
	private long id;
	
	@Column(nullable = false)
	@NotEmpty(message = "{empleado.nombre.novacio}")
	private String nombre;
	
	@Email(message = "{empleado.email.valido}")
	private String email;
	
	private String telefono;
	
	private boolean directivo;
	
	private String imagen;
	
	public Empleado() { }
	
	public Empleado(long id, String nombre, String email, String telefono, boolean directivo)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.setDirectivo(directivo);
	}

	public Empleado(@NotEmpty(message = "{empleado.nombre.novacio}") String nombre,
			@Email(message = "{empleado.email.valido}") String email, String telefono, boolean directivo)
	{
		super();
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.directivo = directivo;
	}

	public Empleado(@Min(value = 1, message = "{empleado.id.mayorquecero}") long id,
			@NotEmpty(message = "{empleado.nombre.novacio}") String nombre,
			@Email(message = "{empleado.email.valido}") String email, String telefono, boolean directivo, String imagen)
	{
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.directivo = directivo;
		this.imagen = imagen;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public boolean isDirectivo()
	{
		return directivo;
	}

	public void setDirectivo(boolean directivo)
	{
		this.directivo = directivo;
	}

	public String getImagen()
	{
		return imagen;
	}

	public void setImagen(String imagen)
	{
		this.imagen = imagen;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (directivo ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (directivo != other.directivo)
			return false;
		if (email == null)
		{
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (imagen == null)
		{
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (nombre == null)
		{
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null)
		{
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Empleado [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", directivo=" + directivo + ", imagen=" + imagen + "]";
	}

	
	
	
}
