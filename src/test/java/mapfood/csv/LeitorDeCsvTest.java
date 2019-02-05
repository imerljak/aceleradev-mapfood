package mapfood.csv;

import mapfood.model.dto.ClienteDTO;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeitorDeCsvTest {

    @Test
    public void deveRetornarFalseSeCaminhoArquivoEstiverCerto() {
        LeitorDeCsv leitor = new LeitorDeCsv();

        assertFalse(leitor.lerDados(ClienteDTO.class, "/csv/clientes.csv").isEmpty());
    }

    @Test
    public void deveRetornarTrueSeCaminhoArquivoEstiverErrado() {
        LeitorDeCsv leitor = new LeitorDeCsv();

        assertTrue(leitor.lerDados(ClienteDTO.class, "/resources/clientes.csv").isEmpty());
    }
}
