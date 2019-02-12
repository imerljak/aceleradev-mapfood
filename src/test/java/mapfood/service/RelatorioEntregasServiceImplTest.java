package mapfood.service;

import mapfood.dto.RelatorioEntrega;
import mapfood.model.mongodb.Entrega;
import mapfood.repository.no_sql.EntregaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RelatorioEntregasServiceImplTest {

    @Mock
    private EntregaRepository repository;

    @InjectMocks
    private RelatorioEntregasServiceImpl service;

    @Test
    public void deveChamarRepositoryQuandoForSalvar() {
        service.salvar(new Entrega());
        verify(repository, times(1)).save(Mockito.any());
    }

    @Test
    public void reveRetornarRelatorioEntrega() {

        final RelatorioEntrega relatorioEntrega = service.getRelatorioEntregas("ID_TEST", 1);

        verify(repository, times(1))
                .findAllByIdEstabelecimentoAndDataSolicitacaoIsAfter(Mockito.anyString(), Mockito.any());

        assertNotNull(relatorioEntrega);
    }


}