package mapfood.utils;

import mapfood.model.jpa.Motoboy;
import mapfood.model.jpa.Posicao;

import java.util.Comparator;

public class PosicaoComparator {

    private final Posicao origin;

    public PosicaoComparator(Posicao origin) {
        this.origin = origin;
    }

    public Comparator<Motoboy> getMotoboyComparator() {
        return Comparator.comparingDouble(current -> origin.distancia(current.getPosicao()));
    }

    public Comparator<Posicao> getPosicaoComparator() {
        return Comparator.comparingDouble(origin::distancia);
    }

}
