package mapfood.factory;

import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;

public final class MotoboyFactory {

    public static MotoboyDTO getInstance(Motoboy motoboy) {
        final MotoboyDTO motoboyDTO = new MotoboyDTO();
        motoboyDTO.setId(motoboy.getId());
        motoboyDTO.setPosicao(motoboy.getPosicao());

        return motoboyDTO;
    }

    public static Motoboy getInstance(MotoboyDTO motoboyDTO) {
        final Motoboy motoboy = new Motoboy();
        motoboy.setId(motoboyDTO.getId());
        motoboy.setPosicao(motoboyDTO.getPosicao());

        return motoboy;
    }

}
