package mapfood.factory;

import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;
import mapfood.model.jpa.Posicao;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MotoboyFactoryTest {

    @Test
    public void deveRetornarMotoboySePassarDTO() {
        MotoboyDTO motoboyDTO = new MotoboyDTO();
        motoboyDTO.setId(1L);
        motoboyDTO.setPosicao(Posicao.of(-200.00, 200.00));

        Motoboy motoboy = MotoboyFactory.getInstance(motoboyDTO);

        assertEquals(motoboyDTO.getId(), motoboy.getId());
        assertEquals(motoboyDTO.getPosicao(), motoboy.getPosicao());
    }

    @Test
    public void deveRetornarDTOSePassarMotoboy() {
        Motoboy motoboy = new Motoboy();
        motoboy.setId(1L);
        motoboy.setPosicao(Posicao.of(-200.00, 200.00));

        MotoboyDTO motoboyDto = MotoboyFactory.getInstance(motoboy);

        assertEquals(motoboy.getId(), motoboyDto.getId());
        assertEquals(motoboy.getPosicao(), motoboyDto.getPosicao());
    }

}