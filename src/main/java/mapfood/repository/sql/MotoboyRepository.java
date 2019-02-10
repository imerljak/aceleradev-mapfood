package mapfood.repository.sql;

import mapfood.model.jpa.Motoboy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface MotoboyRepository extends JpaRepository<Motoboy, Long> {

    @Query("from Motoboy m")
    Stream<Motoboy> streamAll();
}
