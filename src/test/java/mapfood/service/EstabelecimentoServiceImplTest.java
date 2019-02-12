package mapfood.service;

import mapfood.dto.EstabelecimentoDTO;
import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.model.jpa.Posicao;
import mapfood.model.mongodb.Estabelecimento;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EstabelecimentoServiceImplTest {

    @Mock
    private EstabelecimentoRepository repository;

    @InjectMocks
    private EstabelecimentoServiceImpl service;

    @Test(expected = EstabelecimentoNaoEncontradoException.class)
    public void deveJogarExcecaoQuandoNaoEncontrarPorId() {
        when(repository.findById("ID_TESTE")).thenReturn(Optional.empty());
        service.findById("ID_TESTE");
    }

    @Test
    public void deveRetornarDTOQuandoIdExistir() {
        when(repository.findById("ID_TESTE")).thenReturn(newEstabelecimento("ID_TESTE"));
        final EstabelecimentoDTO dto = service.findById("ID_TESTE");

        assertNotNull(dto);
    }

    private Optional<Estabelecimento> newEstabelecimento(String id) {
        final Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(id);
        estabelecimento.setNome("NOME_TESTE");
        estabelecimento.setTipoDeComida("TIPO_COMIDA_TESTE");
        estabelecimento.setCidade("CIDADE_TESTE");
        estabelecimento.setPosicao(Posicao.of(-1D, 1D));

        return Optional.of(estabelecimento);
    }

}