package mapfood.service;

import mapfood.model.mongodb.Estabelecimento;

import java.util.Optional;

public interface EstabelecimentoService {

    /**
     * Retorna estabelecimento pelo id
     * @param id
     * @return estabelecimento
     */
    Optional<Estabelecimento> findById(String id);


    /*
     * TODO: método para retornar relalório de entregas do estabelecimento.
     */
}
