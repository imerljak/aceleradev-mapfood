package mapfood.service;

import mapfood.factory.ClienteFactory;
import mapfood.model.dto.ClienteDTO;
import mapfood.repository.sql.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {


    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteDTO> buscaTodos() {
        return repository.findAll()
                .parallelStream()
                .map(ClienteFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDTO> buscaPorId(Long id) {
        return repository.findById(id).map(ClienteFactory::getInstance);
    }

}
