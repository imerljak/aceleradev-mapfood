package mapfood.service;

import mapfood.model.dto.ClienteDTO;
import mapfood.model.mongodb.DadosEntrega;
import java.util.List;

public interface DadosEntregaService {

    List<DadosEntrega> buscaPorIdAndAfterData(String id, int days);


}
