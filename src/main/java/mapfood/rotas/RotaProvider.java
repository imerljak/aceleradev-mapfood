package mapfood.rotas;

import mapfood.dto.Rota;
import mapfood.model.jpa.Posicao;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RotaProvider {

    CompletableFuture<Rota> getRota(Posicao motoboy, Posicao estabelecimento, List<Posicao> clientes);

}
