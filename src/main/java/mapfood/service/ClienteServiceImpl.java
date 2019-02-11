package mapfood.service;

import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.factory.ClienteFactory;
import mapfood.model.dto.ClienteDTO;
import mapfood.repository.sql.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteDTO> buscaTodos() {

        logger.info("Buscando todos clientes");

        return repository.findAll()
                .parallelStream()
                .map(ClienteFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(cacheNames = "clienteCache")
    public ClienteDTO buscaPorId(Long id) {

        logger.info("Buscando clientes por id: {}", id);

        return repository.findById(id)
                .map(ClienteFactory::getInstance)
                .orElseThrow(() ->
                        new ClienteNaoEncontradoException(id));
    }

}
