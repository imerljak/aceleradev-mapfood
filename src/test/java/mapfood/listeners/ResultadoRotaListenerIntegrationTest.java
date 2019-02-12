package mapfood.listeners;

import mapfood.dto.ResultadoRota;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ResultadoRotaListenerIntegrationTest {

    @Autowired
    private ApplicationEventPublisher publisher;

    @MockBean
    private ResultadoRotaListener listener;

    @Test
    public void deveReceberEventoQuandoForEmitido() {
        final ResultadoRota resultadoRota = newResultadoRota();

        publisher.publishEvent(resultadoRota);

        verify(listener, times(1)).handleResultadoRota(resultadoRota);
    }

    private ResultadoRota newResultadoRota() {
        final ResultadoRota resultadoRota = new ResultadoRota();
        resultadoRota.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        resultadoRota.setConsumoCombustivel(200);
        resultadoRota.setIdMotoboy(1L);

        return resultadoRota;
    }

}