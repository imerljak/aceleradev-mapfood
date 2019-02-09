package mapfood.factory;

import com.vividsolutions.jts.geom.Point;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;

public final class MotoboyFactory {

    public static MotoboyDTO getInstance(Motoboy motoboy) {
        final MotoboyDTO motoboyDTO = new MotoboyDTO();
        motoboyDTO.setId(motoboy.getId());
        motoboyDTO.setLatitude(motoboy.getPosicao().getY());
        motoboyDTO.setLongitude(motoboy.getPosicao().getX());

        return motoboyDTO;
    }

    public static Motoboy getInstance(MotoboyDTO motoboyDTO) {
        final Motoboy motoboy = new Motoboy();
        motoboy.setId(motoboyDTO.getId());

        Point point = new PointFactory().fromLatLong(motoboyDTO.getLatitude(), motoboyDTO.getLongitude());
        motoboy.setPosicao(point);

        return motoboy;
    }

}
