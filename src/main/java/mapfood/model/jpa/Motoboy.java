package mapfood.model.jpa;

import com.vividsolutions.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "motoboys")
@Entity
public class Motoboy {

    @Id
    @GeneratedValue
    private Long id;

    private Point latitude;

    private Point longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Point getLatitude() {
        return latitude;
    }

    public void setLatitude(Point latitude) {
        this.latitude = latitude;
    }

    public Point getLongitude() {
        return longitude;
    }

    public void setLongitude(Point longitude) {
        this.longitude = longitude;
    }
}
