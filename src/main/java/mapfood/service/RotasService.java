package mapfood.service;

import com.google.maps.model.DirectionsResult;
import mapfood.model.dto.SolicitacaoEntrega;

public interface RotasService {

    /**
     * Retorna dados com a melhor roda para a solicitação de entrega informada.
     *
     * @param solicitacaoEntrega solicitacao de entrega para calcular rota.
     * @return Lista de RotaEntrega
     */
    DirectionsResult getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega);

}
