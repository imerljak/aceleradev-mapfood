package mapfood.service;

import mapfood.dto.ResultadoRota;
import mapfood.dto.SolicitacaoEntrega;

import java.util.concurrent.CompletableFuture;

public interface RotasService {

    /**
     * Retorna dados com a melhor roda para a solicitação de entrega informada.
     *
     * @param solicitacaoEntrega solicitacao de entrega para calcular rota.
     * @return Lista de {@link ResultadoRota}
     */
    CompletableFuture<ResultadoRota> getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega);

}
