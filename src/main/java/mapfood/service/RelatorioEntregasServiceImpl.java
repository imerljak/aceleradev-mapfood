package mapfood.service;

import mapfood.factory.EntregaFactory;
import mapfood.model.dto.EntregaDTO;
import mapfood.model.dto.RelatorioEntrega;
import mapfood.model.mongodb.Entrega;
import mapfood.repository.no_sql.EntregaRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioEntregasServiceImpl implements RelatorioEntregasService {

    private final EntregaRepository repository;

    public RelatorioEntregasServiceImpl(EntregaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RelatorioEntrega getRelatorioEntregas(String id, int days) {
        Instant diaInicial = Instant.now().minus(days, ChronoUnit.DAYS);
        final List<EntregaDTO> entregas = repository
                .findAllByIdEstabelecimentoAndDataSolicitacaoIsAfter(id, diaInicial)
                .parallelStream()
                .map(EntregaFactory::getInstance)
                .collect(Collectors.toList());

        final RelatorioEntrega relatorio = new RelatorioEntrega(id);
        relatorio.setEntregas(entregas);

        return relatorio;
    }

    @Override
    public void salvar(Entrega entrega) {
        repository.save(entrega);
    }
}
