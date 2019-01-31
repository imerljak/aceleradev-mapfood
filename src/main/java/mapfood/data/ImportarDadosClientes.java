package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.factory.ClienteFactory;
import mapfood.model.dto.ClienteDTO;
import mapfood.model.jpa.Cliente;
import mapfood.repository.sql.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosClientes implements ImportadorDeDados {

    private final ClienteRepository repository;

    @Lazy
    private final ImportarDadosMotoboys importarDadosMotoboys;

    @Autowired
    public ImportarDadosClientes(ClienteRepository repository, ImportarDadosMotoboys importarDadosMotoboys) {
        this.repository = repository;
        this.importarDadosMotoboys = importarDadosMotoboys;
    }

    @Override
    public void importar() {

        if (repository.count() == 0) {
            List<Cliente> clientes = new LeitorDeCsv().lerDados(ClienteDTO.class, "/csv/clientes.csv")
                    .stream()
                    .map(ClienteFactory::getInstance)
                    .collect(Collectors.toList());

            repository.saveAll(clientes);
        }

        proximoPasso();
    }

    @Override
    public void proximoPasso() {
        importarDadosMotoboys.importar();
    }

}
