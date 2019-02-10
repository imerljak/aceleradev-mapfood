package mapfood.service;

import mapfood.model.dto.ResultadoRota;
import mapfood.model.dto.SolicitacaoEntrega;

public interface RotasService {

    /**
     * Retorna dados com a melhor roda para a solicitação de entrega informada.
     *
     * @param solicitacaoEntrega solicitacao de entrega para calcular rota.
     * @return Lista de RotaEntrega
     */
    ResultadoRota getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega);

}
