package mapfood.utils;

import mapfood.model.jpa.Posicao;

import java.util.Comparator;

public class PosicaoComparator {

    private final Posicao origin;

    public PosicaoComparator(Posicao origin) {
        this.origin = origin;
    }

    public Comparator<Posicao> getPosicaoComparator() {
        return Comparator.comparingDouble(origin::distancia);
    }

}
