package mapfood.service;

import mapfood.dto.EntregaDTO;
import mapfood.dto.RelatorioEntrega;
import mapfood.factory.EntregaFactory;
import mapfood.model.mongodb.Entrega;
import mapfood.repository.no_sql.EntregaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioEntregasServiceImpl implements RelatorioEntregasService {

    private final Logger logger = LoggerFactory.getLogger(RelatorioEntregasServiceImpl.class);

    private final EntregaRepository repository;

    public RelatorioEntregasServiceImpl(EntregaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RelatorioEntrega getRelatorioEntregas(String id, int days) {

        Instant diaInicial = Instant.now().minus(days, ChronoUnit.DAYS);

        logger.info("Retornando relatório para: {}, a partir de: {}", id, diaInicial);

        final List<EntregaDTO> entregas = repository
                .findAllByIdEstabelecimentoAndDataSolicitacaoIsAfter(id, diaInicial)
                .parallelStream()
                .map(EntregaFactory::getInstance)
                .collect(Collectors.toList());

        return new RelatorioEntrega(id, entregas);
    }

    @Override
    public void salvar(Entrega entrega) {

        logger.info("Salvando uma entrega: {}", entrega);

        repository.save(entrega);
    }
}
