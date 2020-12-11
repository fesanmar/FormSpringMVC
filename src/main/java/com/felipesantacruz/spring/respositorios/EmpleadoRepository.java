package com.felipesantacruz.spring.respositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.felipesantacruz.spring.modelo.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> 
{
	// Custom Query by method name
	List<Empleado> findByNombreContainsIgnoreCaseOrEmailContainsIgnoreCaseOrTelefonoContainsIgnoreCase(String nombre, String email, String teleono);
	
	// The arguments can be passed by position (?1) or by name (:str)
	@Query("select e from Empleado e where lower(e.nombre) like lower(concat('%', ?1,'%')) or lower(e.email) like lower(concat('%', ?1,'%')) or lower(telefono) like lower(concat('%', ?1,'%'))")
	List<Empleado> findByNombreEmailOrPhone(String str);
	
	@Query(value = "SELECT * FROM  empleado WHERE lower(nombre) LIKE lower(concat('%', ?1,'%')) OR LOWER(email) LIKE lower(concat('%', ?1,'%')) OR lower(telefono) LIKE lower(concat('%', ?1,'%'))", nativeQuery= true)
	List<Empleado> findByNombreEmailOrPhoneNative(String str);
	
	@Query("select e from Empleado e where (lower(e.nombre) like lower(concat('%', ?1,'%')) or lower(e.email) like lower(concat('%', ?1,'%')) or lower(telefono) like lower(concat('%', ?1,'%'))) and directivo = ?2")
	List<Empleado> findByNombreEmailOrPhoneAndIsDerectivo(String str, boolean isDirectivo);
}
