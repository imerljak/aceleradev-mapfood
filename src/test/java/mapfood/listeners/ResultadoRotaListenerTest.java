package mapfood.listeners;

import mapfood.dto.ResultadoRota;
import mapfood.service.RelatorioEntregasService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ResultadoRotaListenerTest {

    @Mock
    private RelatorioEntregasService service;

    @InjectMocks
    private ResultadoRotaListener listener;

    @Test
    public void deveChamarServiceParaSalvarPedido() {
        final ResultadoRota resultadoRota = newResultadoRota();
        listener.handleResultadoRota(resultadoRota);
        verify(service, times(1)).salvar(Mockito.any());
    }

    private ResultadoRota newResultadoRota() {
        final ResultadoRota resultadoRota = new ResultadoRota();
        resultadoRota.setIdEstabelecimento("ID_ESTABELECIMENTO_TEST");
        resultadoRota.setConsumoCombustivel(200);
        resultadoRota.setIdMotoboy(1L);

        return resultadoRota;
    }

}