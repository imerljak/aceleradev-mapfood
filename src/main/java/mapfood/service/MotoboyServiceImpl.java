package mapfood.service;

import com.vividsolutions.jts.geom.Point;
import mapfood.factory.MotoboyFactory;
import mapfood.factory.PointFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.repository.sql.MotoboyRepository;
import mapfood.spatial.CoordinateComparator;
import mapfood.spatial.GeoUtils;
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
                .stream()
                .map(MotoboyFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<MotoboyDTO> buscaMaisProximo(Double latitude, Double longitude, Double raioEmKm) {

        Point pontoOrigem = new PointFactory().fromLatLong(latitude, longitude);

        CoordinateComparator comparator = new CoordinateComparator(pontoOrigem.getCoordinate());

        // TODO: Melhorar essa consulta.
        return repository.streamAll()
                .parallel()
                .filter(motoboy -> GeoUtils.haversineDistance(pontoOrigem, motoboy.getPosicao()) < raioEmKm)
                .min(comparator.getMotoboyComparator())
                .map(MotoboyFactory::getInstance);

    }

    @Override
    public Optional<MotoboyDTO> buscaPorId(Long id) {
        return repository.findById(id).map(MotoboyFactory::getInstance);
    }

}
