package mapfood.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import mapfood.factory.CoordinateFactory;
import mapfood.factory.MotoboyFactory;
import mapfood.factory.MyGeometryFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.repository.sql.MotoboyRepository;
import mapfood.spatial.CoordinateComparator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    private final CoordinateFactory coordinateFactory = new CoordinateFactory();
    private final MyGeometryFactory geometryFactory = new MyGeometryFactory();

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
    public List<MotoboyDTO> buscaPorAproximacao(Double latitude, Double longitude, Double raioEmKm) {

        Coordinate coordinate = coordinateFactory.getInstance(latitude, longitude);
        Geometry geometry = geometryFactory.createCircle(coordinate, raioEmKm);

        CoordinateComparator coordinateComparator = new CoordinateComparator(coordinate);

        return repository.findByPosicaoIsWithin(geometry)
                .stream()
                .sorted(coordinateComparator.getMotoboyComparator())
                .map(MotoboyFactory::getInstance)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MotoboyDTO> buscaPorId(Long id) {
        return repository.findById(id).map(MotoboyFactory::getInstance);
    }

}
