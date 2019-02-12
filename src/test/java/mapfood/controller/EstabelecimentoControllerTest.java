package mapfood.controller;

import mapfood.dto.EstabelecimentoDTO;
import mapfood.exceptions.EstabelecimentoNaoEncontradoException;
import mapfood.model.jpa.Posicao;
import mapfood.repository.no_sql.EstabelecimentoRepository;
import mapfood.service.EstabelecimentoService;
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
@WebMvcTest(EstabelecimentoController.class)
@AutoConfigureRestDocs
public class EstabelecimentoControllerTest {

    @MockBean
    private EstabelecimentoRepository repository;

    @MockBean
    private EstabelecimentoService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarTodosEstabelecimentos() throws Exception {
        when(service.findAll()).thenReturn(Collections.singletonList(newEstabelecimento()));

        this.mockMvc.perform(get("/v1/estabelecimento"))
                .andExpect(status().isOk())
                .andDo(document("estabelecimento", responseFields(
                        fieldWithPath("[].nome").description("Nome do estabelecimento"),
                        fieldWithPath("[].cidade").description("Cidade do estabelecimento"),
                        fieldWithPath("[].longitude").description("Longitude do estabelecimento"),
                        fieldWithPath("[].latitude").description("Latitude do estabelecimento"),
                        fieldWithPath("[].tipoDeComida").description("Tipo de comida servida pelo estabelecimento")
                )));
    }

    @Test
    public void deveRetornarEstabelecimentoQuandoInformadoIdCorreto() throws Exception {
        when(service.findById("ID_TEST")).thenReturn(newEstabelecimento());

        this.mockMvc.perform(get("/v1/estabelecimento/{id}", "ID_TEST"))
                .andExpect(status().isOk())
                .andDo(document("estabelecimento/:id", responseFields(
                        fieldWithPath("nome").description("Nome do estabelecimento"),
                        fieldWithPath("cidade").description("Cidade do estabelecimento"),
                        fieldWithPath("longitude").description("Longitude do estabelecimento"),
                        fieldWithPath("latitude").description("Latitude do estabelecimento"),
                        fieldWithPath("tipoDeComida").description("Tipo de comida servida pelo estabelecimento")
                )));
    }

    @Test
    public void deveRetornarErroComStatus404CasoNaoExistirPorId() throws Exception {
        when(service.findById("ID_TEST")).thenThrow(new EstabelecimentoNaoEncontradoException("ID_TEST"));

        this.mockMvc.perform(get("/v1/estabelecimento/{id}", "ID_TEST"))
                .andExpect(status().isNotFound())
                .andDo(document("estabelecimento/:id", responseFields(
                        fieldWithPath("mensagem").description("Mensagem de erro")
                )));
    }


    private EstabelecimentoDTO newEstabelecimento() {
        final EstabelecimentoDTO dto = new EstabelecimentoDTO();
        dto.setNome("NOME_TEST");
        dto.setCidade("CIDADE_TEST");
        dto.setTipoDeComida("TIPO_COMIDA_TEST");
        dto.setPosicao(Posicao.of(-1D, 1D));

        return dto;
    }

}