package mapfood.service;

import mapfood.dto.ClienteDTO;
import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.model.jpa.Cliente;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test(expected = ClienteNaoEncontradoException.class)
    public void deveLancarExcecaoQuandoNaoEncontrarPorId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        clienteService.buscaPorId(1L);
    }

    @Test
    public void deveRetornarDTOQuandoEncontrarPorId() {
        when(repository.findById(1L)).thenReturn(newCLiente(1L));
        final ClienteDTO dto = clienteService.buscaPorId(1L);

        assertEquals(new Long(1L), dto.getId());
    }

    private Optional<Cliente> newCLiente(Long id) {
        final Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setPosicao(Posicao.of(-1D, 1D));

        return Optional.of(cliente);
    }

}