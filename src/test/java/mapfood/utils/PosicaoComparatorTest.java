package mapfood.utils;

import mapfood.model.jpa.Posicao;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PosicaoComparatorTest {


    @Test
    public void deveOrdenarAscendente() {

        final Posicao origem = Posicao.of(-30.176509, -50.215623);

        final Posicao a = Posicao.of(-30.177687, -50.214325);
        final Posicao b = Posicao.of(-30.176917, -50.214464);

        final List<Posicao> posicoes = Arrays.asList(a, b);

        posicoes.sort(new PosicaoComparator(origem).getPosicaoComparator());

        assertEquals(posicoes.get(0), b);
    }

}