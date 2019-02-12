package mapfood.controller;

import mapfood.dto.MotoboyDTO;
import mapfood.exceptions.MotoboyNaoEncontradoException;
import mapfood.model.jpa.Posicao;
import mapfood.repository.sql.MotoboyRepository;
import mapfood.service.MotoboyService;
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
@WebMvcTest(MotoboyController.class)
@AutoConfigureRestDocs
public class MotoboyControllerTest {

    @MockBean
    private MotoboyRepository repository;

    @MockBean
    private MotoboyService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarListaDeClientes() throws Exception {

        when(service.buscaTodos()).thenReturn(Collections.singletonList(newMotoboy()));

        this.mockMvc.perform(get("/v1/motoboy"))
                .andExpect(status().isOk())
                .andDo(document("motoboy", responseFields(
                        fieldWithPath("[].id").description("Id do motoboy"),
                        fieldWithPath("[].latitude").description("Latitude do motoboy"),
                        fieldWithPath("[].longitude").description("Longitude do motoboy")
                )));
    }

    @Test
    public void deveRetornarClientePorId() throws Exception {
        when(service.buscaPorId(1L)).thenReturn(newMotoboy());

        this.mockMvc.perform(get("/v1/motoboy/{id}", 1L))
                .andExpect(status().isOk())
                .andDo(document("motoboy/:id", responseFields(
                        fieldWithPath("id").description("Id do motoboy"),
                        fieldWithPath("latitude").description("Latitude do motoboy"),
                        fieldWithPath("longitude").description("Longitude do motoboy")
                )));
    }

    @Test
    public void deveRetornarErroComStatus404CasoNaoExistirPorId() throws Exception {
        when(service.buscaPorId(1L)).thenThrow(new MotoboyNaoEncontradoException("Motoboy não encontrado."));

        this.mockMvc.perform(get("/v1/motoboy/{id}", 1L))
                .andExpect(status().isNotFound())
                .andDo(document("motoboy/:id", responseFields(
                        fieldWithPath("mensagem").description("Mensagem de erro")
                )));
    }

    private MotoboyDTO newMotoboy() {
        final MotoboyDTO dto = new MotoboyDTO();
        dto.setPosicao(Posicao.of(-1D, 1D));

        return dto;
    }

}