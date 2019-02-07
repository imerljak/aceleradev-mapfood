package mapfood.service;

import mapfood.model.mongodb.Estabelecimento;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    public EstabelecimentoServiceImpl(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Estabelecimento> findById(String id) {
        return repository.findById(id);
    }
}
