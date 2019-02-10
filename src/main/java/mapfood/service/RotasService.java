package mapfood.service;

import mapfood.model.dto.SaidaDTO;
import mapfood.model.dto.SolicitacaoEntrega;

public interface RotasService {

    /**
     * Retorna dados com a melhor roda para a solicitação de entrega informada.
     *
     * @param solicitacaoEntrega solicitacao de entrega para calcular rota.
     * @return Lista de RotaEntrega
     */
    SaidaDTO getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega);

}
