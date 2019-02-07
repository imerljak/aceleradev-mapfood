package mapfood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import mapfood.factory.ClienteFactory;
import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import mapfood.repository.sql.ClienteRepository;


public class ClienteServiceImpl implements ClienteService {
	

	@Autowired
	private ClienteRepository cliente;
	
	@Override
	public List<ClienteDTO> buscaTodos() {
		return StreamSupport.stream(cliente.findAll().spliterator(), false).map(ClienteFactory::getInstance).collect(Collectors.toList());	
	}

	@Override
	public Optional<ClienteDTO> buscaPorId(Long id) {
		return null; //ClienteFactory.getInstance(cliente.findById(id);
		
	}

	

}
