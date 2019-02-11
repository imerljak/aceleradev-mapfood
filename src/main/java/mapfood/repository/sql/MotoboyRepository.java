package mapfood.repository.sql;

import mapfood.model.jpa.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    @Query(value = "select * from motoboys m where m.id = " +
            "(select m1.id " +
            "from mapfood.public.motoboys m1 " +
            "order by haversine(?1, ?2, m1.latitude, m1.longitude) asc limit 1)", nativeQuery = true)
    Optional<Motoboy> buscaMaisProximo(Double lat, Double lng);
}
