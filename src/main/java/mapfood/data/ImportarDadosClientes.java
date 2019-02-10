package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.model.jpa.Cliente;
import mapfood.repository.sql.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosClientes implements ImportadorDeDados {

    private final Logger logger = LoggerFactory.getLogger(ImportarDadosClientes.class);

    private final ClienteRepository repository;
    private final LeitorDeCsv leitorDeCsv;

    public ImportarDadosClientes(ClienteRepository repository, LeitorDeCsv leitorDeCsv) {
        this.repository = repository;
        this.leitorDeCsv = leitorDeCsv;
    }

    @Override
    public void importar() {
        if (repository.count() == 0) {
            logger.info("Iniciando importação de dados.");

            List<Cliente> clientes = leitorDeCsv
                    .lerDados(Cliente.class, "/csv/clientes.csv")
                    .parallelStream()
                    .collect(Collectors.toList());

            repository.saveAll(clientes);
        }
    }

}
