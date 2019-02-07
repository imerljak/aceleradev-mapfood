package mapfood.repository.sql;

import com.vividsolutions.jts.geom.Geometry;
import mapfood.model.jpa.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    @Query("from Motoboy m where within(m.posicao, ?1) = true")
    List<Motoboy> findByPosicaoIsWithin(Geometry area);

}
