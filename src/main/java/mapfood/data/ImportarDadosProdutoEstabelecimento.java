package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.factory.ProdutoEstabelecimentoFactory;
import mapfood.model.dto.ProdutoEstabelecimentoDTO;
import mapfood.model.mongodb.ProdutoEstabelecimento;
import mapfood.repository.ProdutoEstabelecimentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImportarDadosProdutoEstabelecimento implements ImportadorDeDados {

    private final Logger logger = LoggerFactory.getLogger(ImportarDadosProdutoEstabelecimento.class);

    private final ProdutoEstabelecimentoRepository repository;
    private final LeitorDeCsv leitorDeCsv;

    public ImportarDadosProdutoEstabelecimento(ProdutoEstabelecimentoRepository repository, LeitorDeCsv leitorDeCsv) {
        this.repository = repository;
        this.leitorDeCsv = leitorDeCsv;
    }


    @Override
    public void importar() {
        if (repository.count() == 0) {
            logger.info("Iniciando importação de dados.");

            List<ProdutoEstabelecimento> produtos = leitorDeCsv
                    .lerDados(ProdutoEstabelecimentoDTO.class, "/csv/produtos-por-estabelecimento.csv")
                    .parallelStream()
                    .map(ProdutoEstabelecimentoFactory::getInstance)
                    .collect(Collectors.toList());

            repository.saveAll(produtos);
        }
    }
}
