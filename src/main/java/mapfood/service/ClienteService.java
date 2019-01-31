package mapfood.service;

import mapfood.model.jpa.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> buscaTodos();

    Optional<Cliente> buscaPorId();

}
