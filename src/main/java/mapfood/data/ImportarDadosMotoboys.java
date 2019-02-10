package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.model.jpa.Motoboy;
import mapfood.repository.sql.MotoboyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosMotoboys implements ImportadorDeDados {

    private final Logger logger = LoggerFactory.getLogger(ImportarDadosMotoboys.class);

    private final MotoboyRepository repository;
    private final LeitorDeCsv leitorDeCsv;

    public ImportarDadosMotoboys(MotoboyRepository repository, LeitorDeCsv leitorDeCsv) {
        this.repository = repository;
        this.leitorDeCsv = leitorDeCsv;
    }

    @Override
    public void importar() {
        if (repository.count() == 0) {
            logger.info("Iniciando importação de dados.");

            List<Motoboy> motoboys = leitorDeCsv
                    .lerDados(Motoboy.class, "/csv/motoboys.csv")
                    .parallelStream()
                    .collect(Collectors.toList());

            repository.saveAll(motoboys);
        }
    }

}
