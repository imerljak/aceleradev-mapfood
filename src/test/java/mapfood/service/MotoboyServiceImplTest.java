package mapfood.service;

import mapfood.factory.PointFactory;
import mapfood.model.dto.MotoboyDTO;
import mapfood.model.jpa.Motoboy;
import mapfood.repository.sql.MotoboyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MotoboyServiceImplTest {

    @Mock
    private MotoboyRepository motoboyRepository;

    @InjectMocks
    private MotoboyServiceImpl motoboyService;

    @Test
    public void deveRetornarMotoboyMaisProximo() {

        final Motoboy motoboyDistance = criaMotoboy(0, -30.025116, -51.215902);
        final Motoboy motoboyProximo = criaMotoboy(1, -30.021771, -51.207923);

        when(motoboyRepository.findAll()).thenReturn(Arrays.asList(motoboyDistance, motoboyProximo));
        final Optional<MotoboyDTO> maisProximo = motoboyService.buscaMaisProximo(-30.022440, -51.204491, 20D);

        assertTrue(maisProximo.isPresent());
        assertEquals(motoboyProximo.getId(), maisProximo.get().getId());
    }

    @Test
    public void naoDeveRetornarCasoNaoExistamMotoboysNaRegiao() {
        final Motoboy motoboyMuitoDistante = criaMotoboy(0, -30.116047, -51.361982);
        final Motoboy motoboyDistante = criaMotoboy(1, -30.089910, -51.327665);

        when(motoboyRepository.findAll()).thenReturn(Arrays.asList(motoboyDistante, motoboyMuitoDistante));
        final Optional<MotoboyDTO> maisProximo = motoboyService.buscaMaisProximo(-30.022440, -51.204491, 14D);
        maisProximo.ifPresent(System.out::println);
        assertFalse(maisProximo.isPresent());
    }

    private Motoboy criaMotoboy(long id, double lat, double lng) {
        final Motoboy motoboy = new Motoboy();

        motoboy.setId(id);
        motoboy.setPosicao(new PointFactory().fromLatLong(lat, lng));

        return motoboy;
    }

}