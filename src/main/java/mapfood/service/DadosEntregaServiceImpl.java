package mapfood.service;

import mapfood.model.mongodb.DadosEntrega;
import mapfood.repository.no_sql.DadosEntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DadosEntregaServiceImpl implements DadosEntregaService {

    private final DadosEntregaRepository repository;

    public DadosEntregaServiceImpl(DadosEntregaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DadosEntrega> buscaRelatorioEntregas(String id, int days) {
        LocalDate primeiroDia = LocalDate.now().minusDays(days);
        return repository.findAllByIdEqualsAndDataSolicitacaoAfter(id, primeiroDia);
    }

    @Override
    public void salvar(DadosEntrega dadosEntrega) {
        repository.save(dadosEntrega);
    }
}
