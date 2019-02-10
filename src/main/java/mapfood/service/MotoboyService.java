package mapfood.service;

import mapfood.model.dto.MotoboyDTO;

import java.util.List;
import java.util.Optional;

public interface MotoboyService {

    /**
     * Retorna uma lista contendo todos os Motoboys registrados na base de dados.
     *
     * @return lista de motoboys
     */
    List<MotoboyDTO> buscaTodos();

    /**
     * Retorna uma lista contendo todos os motoboys por proximidade detro de do raioEmKm
     * ordenados por distancia crescente.
     *
     * @param latitude  latitude inicial
     * @param longitude longitude inicial
     * @param raioEmKm  raio em kms para considerar a pesquisa.
     * @return lista de motoboys ordenada pela distancia relativa a lat/long informada.
     */
    Optional<MotoboyDTO> buscaMaisProximo(Double latitude, Double longitude, Double raioEmKm);

    /**
     * Busca um motoboy pelo seu id e retorna um {@link Optional} contendo o motoboy ou não, caso não exista.
     *
     * @return motoboy, ou não :)
     */
    Optional<MotoboyDTO> buscaPorId(Long id);

}
