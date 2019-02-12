package mapfood.controller;

import mapfood.dto.ClienteDTO;
import mapfood.exceptions.ClienteNaoEncontradoException;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.ClienteRepository;
import mapfood.service.ClienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClienteController.class)
@AutoConfigureRestDocs
public class ClienteControllerTest {

    @MockBean
    private ClienteRepository repository;

    @MockBean
    private ClienteService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarListaDeClientes() throws Exception {

        when(service.buscaTodos()).thenReturn(Collections.singletonList(newCliente()));

        this.mockMvc.perform(get("/v1/cliente"))
                .andExpect(status().isOk())
                .andDo(document("cliente", responseFields(
                        fieldWithPath("[].latitude").description("Latitude do cliente"),
                        fieldWithPath("[].longitude").description("Longitude do cliente")
                )));
    }

    @Test
    public void deveRetornarClientePorId() throws Exception {
        when(service.buscaPorId(1L)).thenReturn(newCliente());

        this.mockMvc.perform(get("/v1/cliente/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("cliente/:id", responseFields(
                        fieldWithPath("latitude").description("Latitude do cliente"),
                        fieldWithPath("longitude").description("Longitude do cliente")
                )));
    }

    @Test
    public void deveRetornarErroComStatus404CasoNaoExistirPorId() throws Exception {
        when(service.buscaPorId(1L)).thenThrow(new ClienteNaoEncontradoException(1L));

        this.mockMvc.perform(get("/v1/cliente/{id}", 1L))
                .andExpect(status().isNotFound())
                .andDo(document("cliente/:id", responseFields(
                        fieldWithPath("mensagem").description("Mensagem de erro")
                )));
    }

    private ClienteDTO newCliente() {
        final ClienteDTO cliente = new ClienteDTO();
        cliente.setPosicao(Posicao.of(-1D, 1D));

        return cliente;
    }

}