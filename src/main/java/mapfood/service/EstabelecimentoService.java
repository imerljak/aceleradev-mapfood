package mapfood.service;

import mapfood.model.dto.EstabelecimentoDTO;

import java.util.List;

public interface EstabelecimentoService {

    /**
     * Retorna estabelecimento pelo id
     *
     * @param id id do estabelecimento
     * @return estabelecimento
     */
    EstabelecimentoDTO findById(String id);

    /**
     * Retorna todos estabelecimentos registrados.
     *
     * @return lista de estabelecimentos
     */
    List<EstabelecimentoDTO> findAll();

    /*
     * TODO: método para retornar relalório de entregas do estabelecimento.
     */
}
