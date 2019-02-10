package mapfood.service;

import mapfood.exceptions.ClienteMuitoDistanteException;
import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.model.dto.*;
import mapfood.model.jpa.Posicao;
import mapfood.rotas.RotaProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RotasServiceImpl implements RotasService {

    private final ApplicationEventPublisher publisher;

    private final RotaProvider rotaProvider;

    private final EstabelecimentoService estabelecimentoService;
    private final ClienteService clienteService;
    private final MotoboyService motoboyService;


    @Value("${mapfood.valores.consumo-motocicleta}")
    public double cosumoMotocicleta;

    @Value("${mapfood.valores.limite-distancia-entrega}")
    private Double limiteKmEntrega;

    @Autowired
    public RotasServiceImpl(ApplicationEventPublisher publisher, EstabelecimentoService estabelecimentoService, ClienteService clienteService, MotoboyService motoboyService, RotaProvider rotaProvider) {
        this.publisher = publisher;
        this.estabelecimentoService = estabelecimentoService;
        this.clienteService = clienteService;
        this.motoboyService = motoboyService;
        this.rotaProvider = rotaProvider;
    }

    @Override
    public CompletableFuture<ResultadoRota> getMelhorRotaPara(SolicitacaoEntrega solicitacaoEntrega) {

        final EstabelecimentoDTO estabelecimentoDTO = estabelecimentoService.findById(solicitacaoEntrega.getIdEstabelecimento())
                .orElseThrow(() -> new EstabelecimentoNaoEncontradoException(solicitacaoEntrega.getIdEstabelecimento()));

        List<ClienteDTO> clientesDosPedidos = getClientesDosPedidos(solicitacaoEntrega.getPedidos());

        validaTodosClientesDentroDaDistanciaLimite(clientesDosPedidos, estabelecimentoDTO.getPosicao());

        final MotoboyDTO motoboyMaisProximo = motoboyService
                .buscaMaisProximo(estabelecimentoDTO.getPosicao(), limiteKmEntrega)
                .orElseThrow(RuntimeException::new);

        List<Posicao> posicaoDosClientes = clientesDosPedidos
                .parallelStream()
                .map(ClienteDTO::getPosicao)
                .collect(Collectors.toList());

        return rotaProvider.getRota(motoboyMaisProximo.getPosicao(), estabelecimentoDTO.getPosicao(), posicaoDosClientes)
                .thenApply(rota -> {
                    final ResultadoRota resultadoRota = new ResultadoRota();
                    resultadoRota.setIdEstabelecimento(solicitacaoEntrega.getIdEstabelecimento());
                    resultadoRota.setIdMotoboy(motoboyMaisProximo.getId());
                    resultadoRota.setRota(rota);
                    resultadoRota.setConsumoCombustivel((resultadoRota.getDistanciaEmMetros() / 1000) / cosumoMotocicleta);

                    return resultadoRota;
                })
                .thenApply(resultadoRota -> {
                    publisher.publishEvent(resultadoRota);

                    return resultadoRota;
                });
    }

    private void validaTodosClientesDentroDaDistanciaLimite(List<ClienteDTO> clientesDosPedidos, Posicao posicaoDeOrigem) {

        clientesDosPedidos.parallelStream()
                .forEach(cliente -> {
                    double distanciaEmKilometros = posicaoDeOrigem.distancia(cliente.getPosicao());

                    if (distanciaEmKilometros > limiteKmEntrega) {
                        throw new ClienteMuitoDistanteException(cliente);
                    }
                });
    }


    private List<ClienteDTO> getClientesDosPedidos(List<Pedido> pedidos) {
        return pedidos
                .stream()
                .map(Pedido::getIdCliente)
                .map(idCliente -> clienteService.buscaPorId(idCliente).orElseThrow(() -> new ClienteNaoEncontradoException(idCliente)))
                .collect(Collectors.toList());
    }


}
