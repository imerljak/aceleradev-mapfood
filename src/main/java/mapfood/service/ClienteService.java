package mapfood.service;

import mapfood.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    /**
     * Retorna uma lista com todos clientes registrados na base de dados
     *
     * @return lista de clientes
     */
    List<ClienteDTO> buscaTodos();

    /**
     * Busca e retorna um cliente pelo seu Id
     *
     * @return opcional de cliente
     */
    ClienteDTO buscaPorId(Long id);

}
