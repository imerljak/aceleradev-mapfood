package mapfood.service;

import mapfood.model.mongodb.Estabelecimento;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public List<Estabelecimento> findAll() {
        return repository.findAll();
    }
}
