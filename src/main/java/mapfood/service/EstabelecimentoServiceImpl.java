package mapfood.service;

import mapfood.factory.EstabelecimentoFactory;
import mapfood.model.dto.EstabelecimentoDTO;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    public EstabelecimentoServiceImpl(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EstabelecimentoDTO> findById(String id) {
        return repository.findById(id).map(EstabelecimentoFactory::getInstance);
    }

    @Override
    public List<EstabelecimentoDTO> findAll() {
        return repository.findAll()
                .parallelStream()
                .map(EstabelecimentoFactory::getInstance)
                .collect(Collectors.toList());
    }

}
