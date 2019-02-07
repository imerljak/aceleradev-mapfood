package mapfood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mapfood.model.dto.MotoboyDTO;
import mapfood.service.MotoboyService;

@RestController
@RequestMapping(path="v1")
public class MotoboyController {

	@Autowired
	private MotoboyService service;
		
	@GetMapping("/motoboy")
	public List<MotoboyDTO> getMotoboys(){
		return service.buscaTodos();		
	}
	
	@GetMapping("/motoboy/{id}")
	public Optional<MotoboyDTO> getMotoboysPorId(@PathVariable Long id){
		return service.buscaPorId(id);		
	}
}
