package mapfood.listeners;

import mapfood.dto.ResultadoRota;
import mapfood.model.mongodb.Entrega;
import mapfood.service.RelatorioEntregasService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ResultadoRotaListener {

    private final RelatorioEntregasService relatorioEntregasService;

    public ResultadoRotaListener(RelatorioEntregasService relatorioEntregasService) {
        this.relatorioEntregasService = relatorioEntregasService;
    }

    @Async
    @EventListener
    public void handleResultadoRota(ResultadoRota resultadoRota) {
        final Entrega entrega = new Entrega();
        entrega.setIdEstabelecimento(resultadoRota.getIdEstabelecimento());
        entrega.setDataSolicitacao(Instant.now());
        entrega.setDistanciaEmMetros(resultadoRota.getDistanciaEmMetros());
        entrega.setDuracaoEmSegundos(resultadoRota.getDuracaoEmSegundos());

        relatorioEntregasService.salvar(entrega);
    }

}
