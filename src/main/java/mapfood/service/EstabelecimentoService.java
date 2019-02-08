package mapfood.service;

import mapfood.model.mongodb.Estabelecimento;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoService {

    /**
     * Retorna estabelecimento pelo id
     *
     * @param id id do estabelecimento
     * @return estabelecimento
     */
    Optional<Estabelecimento> findById(String id);

    /**
     * Retorna todos estabelecimentos registrados.
     *
     * @return lista de estabelecimentos
     */
    List<Estabelecimento> findAll();

    /*
     * TODO: método para retornar relalório de entregas do estabelecimento.
     */
}
