package mapfood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mapfood.model.dto.ClienteDTO;
import mapfood.service.ClienteService;

@RestController
@RequestMapping(path="v1")
public class ClienteController {

	@Autowired
	private ClienteService service;
		
	@GetMapping("/cliente")
	public List<ClienteDTO> getClientes(){
		return service.buscaTodos();		
	}
	
	@GetMapping("/cliente/{id}")
	public Optional<ClienteDTO> getClientesPorId(@PathVariable Long id){
		return service.buscaPorId(id);		
	}
	
}
