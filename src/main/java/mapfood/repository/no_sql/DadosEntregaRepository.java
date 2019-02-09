package mapfood.repository.no_sql;

import mapfood.model.mongodb.DadosEntrega;
import mapfood.model.mongodb.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DadosEntregaRepository extends MongoRepository<DadosEntrega, String> {

    List<DadosEntrega> findAllByIdEqualsAndDataSolicitacaoAfter(String id, LocalDate dataSolicitacao);
}
