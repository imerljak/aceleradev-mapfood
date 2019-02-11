package mapfood.listeners;

import mapfood.model.dto.ResultadoRota;
import mapfood.model.mongodb.DadosEntrega;
import mapfood.service.DadosEntregaService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ResultadoRotaListener {

    private final DadosEntregaService dadosEntregaService;

    public ResultadoRotaListener(DadosEntregaService dadosEntregaService) {
        this.dadosEntregaService = dadosEntregaService;
    }

    @Async
    @EventListener
    public void handleResultadoRota(ResultadoRota resultadoRota) {
        final DadosEntrega dadosEntrega = new DadosEntrega();
        dadosEntrega.setIdEstabelecimento(resultadoRota.getIdEstabelecimento());
        dadosEntrega.setDataSolicitacao(Instant.now());
        dadosEntrega.setDistanciaEmMetros(resultadoRota.getDistanciaEmMetros());
        dadosEntrega.setDuracaoEmSegundos(resultadoRota.getDuracaoEmSegundos());

        dadosEntregaService.salvar(dadosEntrega);
    }

}
