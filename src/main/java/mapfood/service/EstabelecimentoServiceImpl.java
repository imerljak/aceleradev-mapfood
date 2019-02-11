package mapfood.service;

import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.factory.EstabelecimentoFactory;
import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private final Logger logger = LoggerFactory.getLogger(EstabelecimentoServiceImpl.class);

    private final EstabelecimentoRepository repository;

    public EstabelecimentoServiceImpl(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "estabelecimentoCache")
    public EstabelecimentoDTO findById(final String id) {

        logger.info("Buscando estabelecimento pelo id: {}", id);

        return repository.findById(id)
                .map(EstabelecimentoFactory::getInstance)
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException(id));
    }

    @Override
    public List<EstabelecimentoDTO> findAll() {

        logger.info("Buscando todos estabelecimentos.");

        return repository.findAll()
                .parallelStream()
                .map(EstabelecimentoFactory::getInstance)
                .collect(Collectors.toList());
    }

}
