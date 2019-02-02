package mapfood.factory;

import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotoboyFactoryTest {

    @Test
    public void deveRetornarMotoboySePassarDTO() {
        MotoboyDTO motoboyDTO = new MotoboyDTO();
        motoboyDTO.setId(1L);
        motoboyDTO.setLatitude(-200.00);
        motoboyDTO.setLongitude(200.00);

        Motoboy motoboy = MotoboyFactory.getInstance(motoboyDTO);

        assertEquals(motoboyDTO.getId(), motoboy.getId());
        assertEquals(motoboyDTO.getLatitude(), motoboy.getPosicao().getY(), 0);
        assertEquals(motoboyDTO.getLongitude(), motoboy.getPosicao().getX(), 0);
    }

    @Test
    public void deveRetornarDTOSePassarMotoboy() {
        Motoboy motoboy = new Motoboy();
        motoboy.setId(1L);
        motoboy.setPosicao(new PointFactory().fromLatLong(-200.00, 200.00));

        MotoboyDTO motoboyDto = MotoboyFactory.getInstance(motoboy);

        assertEquals(motoboy.getId(), motoboyDto.getId());
        assertEquals(motoboy.getPosicao().getY(), motoboyDto.getLatitude(), 0);
        assertEquals(motoboy.getPosicao().getX(), motoboyDto.getLongitude(), 0);
    }

}