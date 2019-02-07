package mapfood.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import mapfood.factory.MotoboyFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;
import mapfood.repository.sql.MotoboyRepository;

public class MotoboyServiceImpl implements MotoboyService{

	@Autowired
	private MotoboyRepository motoboy;
	
	
	@Override
	public List<MotoboyDTO> buscaTodos() {
		return StreamSupport.stream(motoboy.findAll().spliterator(), false).map(MotoboyFactory::getInstance).collect(Collectors.toList());		
	}

	@Override
	public List<MotoboyDTO> buscaPorAproximacao(Double latitude, Double longitude, Double raioEmKm) {
		
		return null;
	}

	@Override
	public Optional<MotoboyDTO> buscaPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}