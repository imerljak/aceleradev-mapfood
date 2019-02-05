package mapfood.repository.no_sql;

import mapfood.model.mongodb.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento, String> {

}
