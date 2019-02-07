package mapfood.service;

import mapfood.model.dto.SolicitacaoEntrega;
import mapfood.model.dto.RotaEntrega;

import java.util.List;

public interface RotasService {

    /**
     * Retorna dados com a melhor roda para a solicitação de entrega informada.
     *
     * @param solicitacaoEntrega solicitacao de entrega para calcular rota.
     * @return Lista de RotaEntrega
     */
    List<RotaEntrega> getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega);

}