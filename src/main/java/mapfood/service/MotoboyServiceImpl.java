package mapfood.service;

import mapfood.factory.MotoboyFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.MotoboyRepository;
import mapfood.utils.GeoUtils;
import mapfood.utils.PosicaoComparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    private final MotoboyRepository repository;

    public MotoboyServiceImpl(MotoboyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MotoboyDTO> buscaTodos() {
        return repository.findAll()
                .parallelStream()
                .map(MotoboyFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<MotoboyDTO> buscaMaisProximo(Posicao posicao, Double raioEmKm) {
        PosicaoComparator comparator = new PosicaoComparator(posicao);

        // TODO: Melhorar essa consulta.
        return repository.streamAll()
                .parallel()
                .filter(motoboy -> GeoUtils.haversineDistance(posicao, motoboy.getPosicao()) < raioEmKm)
                .min(comparator.getMotoboyComparator())
                .map(MotoboyFactory::getInstance);

    }

    @Override
    public Optional<MotoboyDTO> buscaPorId(Long id) {
        return repository.findById(id).map(MotoboyFactory::getInstance);
    }

}
