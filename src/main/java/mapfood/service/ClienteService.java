package mapfood.service;

import mapfood.model.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

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
    Optional<ClienteDTO> buscaPorId(Long id);
    
    

}
