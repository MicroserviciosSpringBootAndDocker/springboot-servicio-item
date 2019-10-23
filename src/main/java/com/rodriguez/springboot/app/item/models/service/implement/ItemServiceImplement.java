package com.rodriguez.springboot.app.item.models.service.implement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rodriguez.springboot.app.item.models.Item;
//import com.rodriguez.springboot.app.item.models.Producto;
import com.rodriguez.springboot.app.item.models.service.ItemService;
import com.rodriguez.springboot.app.commons.models.Producto;

@Service("serviceRestRemplate")
public class ItemServiceImplement implements ItemService{
	
	@Autowired
	private RestTemplate clienteRest;
	

	@Override
	public List<Item> findAll() {
		//List<Producto> producto = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		List<Producto> producto = Arrays.asList(clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		return producto.stream().map(p -> new Item(p , 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		Map<String, String> pathVariables = new HashMap<String,String>();
		pathVariables.put("id", id.toString());
		//Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}",Producto.class, pathVariables);
		Producto producto = clienteRest.getForObject("http://localhost:8001/ver/{id}",Producto.class, pathVariables);
		return new Item(producto,cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		HttpEntity<Producto>body= new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://localhost:8001/crear", HttpMethod.POST, body, Producto.class);
		Producto prouctoRespose = response.getBody();
		return prouctoRespose;
	}

	@Override
	public Producto update(Producto producto, Long id) {
		
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		HttpEntity<Producto>body= new HttpEntity<Producto>(producto);
		ResponseEntity<Producto> response = clienteRest.exchange("http://localhost:8001/editar/{id}"
				,HttpMethod.PUT,body ,Producto.class, pathVariable );
		
		return response.getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariable = new HashMap<String,String>();
		pathVariable.put("id", id.toString());
		clienteRest.delete("http://localhost:8001/eliminar/{id}",pathVariable);
		
	}

}
