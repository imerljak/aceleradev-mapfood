package mapfood.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import mapfood.factory.CoordinateFactory;
import mapfood.factory.MotoboyFactory;
import mapfood.factory.MyGeometryFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.repository.sql.MotoboyRepository;
import mapfood.spatial.GeoUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoboyServiceImpl implements MotoboyService {

    private final CoordinateFactory coordinateFactory = new CoordinateFactory();
    private final GeometryFactory geometryFactory = new GeometryFactory();

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
    public Optional<MotoboyDTO> buscaMaisProximo(Double latitude, Double longitude, Double raioEmKm) {

        Coordinate coordinate = coordinateFactory.getInstance(latitude, longitude);

        final Double raioEmMetros = raioEmKm * 1000;
        final Double raioEmGraus = GeoUtils.metersToDegrees(raioEmMetros);

        Geometry centro = geometryFactory.createPoint(coordinate);
        Geometry area = new MyGeometryFactory().createCircle(coordinate, raioEmGraus);

        return repository
                .buscarMaisProximo(area, centro, PageRequest.of(0, 1))
                .stream()
                .map(MotoboyFactory::getInstance)
                .findFirst();
    }

    @Override
    public Optional<MotoboyDTO> buscaPorId(Long id) {
        return repository.findById(id).map(MotoboyFactory::getInstance);
    }

}
