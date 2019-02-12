package mapfood.service;

import mapfood.exceptions.MotoboyNaoEncontradoException;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.MotoboyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MotoboyServiceImplTest {

    @Mock
    private MotoboyRepository repository;

    @InjectMocks
    private MotoboyServiceImpl service;

    @Test(expected = MotoboyNaoEncontradoException.class)
    public void deveJogarExcecaoQuandoNaoEncontrarMotoboyProximo() {
        Mockito.when(repository.buscaMaisProximo(-1D, 1D)).thenReturn(Optional.empty());
        service.buscaMaisProximo(Posicao.of(-1D, 1D), 10D);
    }

    @Test(expected = MotoboyNaoEncontradoException.class)
    public void deveJogarExcecaoQuandoNaoEncontrarMotoboyPorId() {
        Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());
        service.buscaPorId(1L);
    }

}