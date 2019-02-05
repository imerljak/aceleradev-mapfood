package mapfood.repository;

import mapfood.model.mongodb.ProdutoEstabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoEstabelecimentoRepository extends MongoRepository<ProdutoEstabelecimento, String> {
}
