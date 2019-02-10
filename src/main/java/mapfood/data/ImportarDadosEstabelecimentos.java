package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosEstabelecimentos implements ImportadorDeDados {

    private final Logger logger = LoggerFactory.getLogger(ImportarDadosEstabelecimentos.class);

    private final EstabelecimentoRepository repository;
    private final LeitorDeCsv leitorDeCsv;

    public ImportarDadosEstabelecimentos(EstabelecimentoRepository repository, LeitorDeCsv leitorDeCsv) {
        this.repository = repository;
        this.leitorDeCsv = leitorDeCsv;
    }

    @Override
    public void importar() {
        if (repository.count() == 0) {
            logger.info("Iniciando importação de dados.");

            List<Estabelecimento> estabelecimentos = leitorDeCsv
                    .lerDados(Estabelecimento.class, "/csv/estabelecimento-por-municipio.csv")
                    .parallelStream()
                    .collect(Collectors.toList());

            repository.saveAll(estabelecimentos);
        }
    }

}
