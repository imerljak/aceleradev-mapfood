package mapfood.data;

import mapfood.repository.sql.MotoboyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ImportarDadosMotoboyTest {

    private ImportarDadosMotoboys importarDadosMotoboys;

    @Mock
    private MotoboyRepository repository;

    @Before
    public void setUp(){
        importarDadosMotoboys = new ImportarDadosMotoboys(repository);
    }

    @Test
    public void naoDeveTerDadosArmazenadosNoBanco(){
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    public void deveTerDadosArmazenadosNoBanco(){
        importarDadosMotoboys.importar();

        assertFalse(repository.findAll().isEmpty());
    }
}
