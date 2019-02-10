package mapfood.data;

import mapfood.csv.LeitorDeCsv;
import mapfood.model.jpa.Cliente;
import mapfood.model.jpa.Motoboy;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.model.mongodb.ProdutoEstabelecimento;
import mapfood.repository.ProdutoEstabelecimentoRepository;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import mapfood.repository.sql.ClienteRepository;
import mapfood.repository.sql.MotoboyRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class ImportadoresDeDadosParamerizedTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final LeitorDeCsv leitorDeCsv;
    private final ImportadorDeDados importador;
    private final CrudRepository repository;
    private final Class<Object> classeDto;
    private final String caminhoCsv;

    @Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{

                {
                        ImportarDadosClientes.class,
                        Cliente.class,
                        ClienteRepository.class,
                        "/csv/clientes.csv"
                },
                {
                        ImportarDadosMotoboys.class,
                        Motoboy.class,
                        MotoboyRepository.class,
                        "/csv/motoboys.csv"
                },
                {
                        ImportarDadosEstabelecimentos.class,
                        Estabelecimento.class,
                        EstabelecimentoRepository.class,
                        "/csv/estabelecimento-por-municipio.csv"
                },
                {
                        ImportarDadosProdutoEstabelecimento.class,
                        ProdutoEstabelecimento.class,
                        ProdutoEstabelecimentoRepository.class,
                        "/csv/produtos-por-estabelecimento.csv"
                },
        });
    }

    public ImportadoresDeDadosParamerizedTest(Class<? extends ImportadorDeDados> importadorClass, Class<Object> classeDto, Class<? extends CrudRepository> repositoryClass, String caminhoCsv) {

        this.repository = mock(repositoryClass);
        this.leitorDeCsv = mock(LeitorDeCsv.class);

        this.importador = buildImportador(importadorClass, repositoryClass);

        this.classeDto = classeDto;
        this.caminhoCsv = caminhoCsv;
    }

    private ImportadorDeDados buildImportador(Class<? extends ImportadorDeDados> importadorClass, Class<? extends CrudRepository> repositoryClass) {
        try {
            return importadorClass
                    .getConstructor(repositoryClass, LeitorDeCsv.class)
                    .newInstance(repository, leitorDeCsv);
        } catch (Exception e) {
            // TODO: Definir uma exceção melhor aqui?
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Test
    public void naoDeveImportarCasoJaExistaDadosNoBanco() {
        when(repository.count()).thenReturn(1L);

        importador.importar();

        verify(leitorDeCsv, never()).lerDados(classeDto, caminhoCsv);
        verify(repository, never()).saveAll(new ArrayList<>());
    }

    @Test
    public void deveImportarCasoNaoTenhaDadosNoBanco() {
        when(repository.count()).thenReturn(0L);

        importador.importar();

        verify(leitorDeCsv, atLeastOnce()).lerDados(classeDto, caminhoCsv);
        verify(repository, times(1)).saveAll(new ArrayList<>());
    }

}
