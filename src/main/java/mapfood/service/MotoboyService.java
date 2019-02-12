package mapfood.service;

import mapfood.dto.MotoboyDTO;
import mapfood.model.jpa.Posicao;

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
     * @param posicao  posicao inicial
     * @param raioEmKm raio em kms para considerar a pesquisa.
     * @return lista de motoboys ordenada pela distancia relativa a lat/long informada.
     */
    MotoboyDTO buscaMaisProximo(Posicao posicao, Double raioEmKm);

    /**
     * Busca um motoboy pelo seu id e retorna um {@link Optional} contendo o motoboy ou não, caso não exista.
     *
     * @return motoboy, ou não :)
     */
    MotoboyDTO buscaPorId(Long id);

}
