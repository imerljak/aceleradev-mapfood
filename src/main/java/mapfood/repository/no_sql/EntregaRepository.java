package mapfood.repository.no_sql;

import mapfood.model.mongodb.Entrega;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface EntregaRepository extends MongoRepository<Entrega, String> {
    List<Entrega> findAllByIdEstabelecimentoAndDataSolicitacaoIsAfter(String id, Instant dataSolicitacao);
}
