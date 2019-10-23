package com.rodriguez.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//import com.rodriguez.springboot.app.item.models.Producto;
import com.rodriguez.springboot.app.commons.models.Producto;

@FeignClient(name="servicio-productos" , url ="localhost:8001")
public interface ProductoClienteRest {
	
	@GetMapping("/listar") //mismo empoind Producto
	public List<Producto> listar();
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
	
	
	@PostMapping("/crear/{id}")
	public Producto save(@RequestBody Producto producto);
	
	
	@PutMapping("/editar/{id}")
	public Producto update(@RequestBody Producto producto , @PathVariable Long id);
	
	@DeleteMapping("/eliminar/{id}")
	public void delete(@PathVariable Long id );

}
