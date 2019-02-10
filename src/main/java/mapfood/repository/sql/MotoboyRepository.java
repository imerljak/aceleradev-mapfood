package mapfood.repository.sql;

import com.vividsolutions.jts.geom.Geometry;
import mapfood.model.jpa.Motoboy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    @Query("from Motoboy m where within(m.posicao, ?1) = true order by distance(m.posicao, ?2) ASC")
    Page<Motoboy> buscarMaisProximo(Geometry distance, Geometry area, Pageable pageable);

}
