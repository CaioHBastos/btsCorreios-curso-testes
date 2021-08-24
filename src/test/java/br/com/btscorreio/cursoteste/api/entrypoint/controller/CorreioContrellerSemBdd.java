package br.com.btscorreio.cursoteste.api.entrypoint.controller;

import br.com.btscorreio.cursoteste.utils.builder.CorreioModelPatternMock;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p>
 *     Classe responsável por validar o cenário onde é testado o componente
 *     da aplicação com o fluxo por completo.
 *
 *     <b>CAMADA ACEITACAO (COMPONENTE)</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CorreioContrellerSemBdd {

    private static final String URL_CORREIO = "/bts_correios/v1/addresses";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;

    /**
     * Método responsável por validar a busca do CEP sem formatação
     * por uma chamada com mockMVC.
     */
    @Test
    public void testGetByCepSucess() throws Exception {
        BDDMockito.given(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET),
                        Mockito.any(), Mockito.any(ParameterizedTypeReference.class)))
                .willReturn(CorreioModelPatternMock.getMockCorreioResponseEntity());

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get(URL_CORREIO)
                .param("cep", "06766200"));

        assertNotNull(resultActions);
        resultActions.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep", Matchers.is("06766200")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.logradouro", Matchers.is("Rua Doutor Carlos Siqueira Neto")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro", Matchers.is("Pq Pinheiros")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade", Matchers.is("Taboão da Serra")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uf", Matchers.is("SP")));
    }
}
