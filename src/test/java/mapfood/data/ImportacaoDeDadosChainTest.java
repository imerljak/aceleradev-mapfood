package mapfood.data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ImportacaoDeDadosChainTest {

    private ImportadorDeDados importador;

    private ImportacaoDeDadosChain chain;

    @Before
    public void before() {
        importador = Mockito.mock(ImportadorDeDados.class);
        chain = new ImportacaoDeDadosChain(Collections.singletonList(importador));
    }

    @Test
    public void deveChamarTodosImportadoresQuandoReceberEvento() {
        chain.onApplicationEvent(null);

        verify(importador, times(1)).importar();
    }

}