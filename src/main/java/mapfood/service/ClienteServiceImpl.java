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


public class ClienteServiceImpl implements ClienteService {
	

	@Autowired
	private MongoOperations mongo;
	
	@Override
	public List<ClienteDTO> buscaTodos() {
		return StreamSupport.stream(mongo.findAll(Cliente.class).spliterator(), false).map(ClienteFactory::getInstance).collect(Collectors.toList());		
	}

	@Override
	public Optional<ClienteDTO> buscaPorId(Long id) {
		return ClienteFactory.getInstance(mongo.findById(id, Cliente.class));
		
	}

	

}
