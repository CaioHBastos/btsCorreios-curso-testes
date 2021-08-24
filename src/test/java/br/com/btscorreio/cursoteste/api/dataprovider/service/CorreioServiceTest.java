package br.com.btscorreio.cursoteste.api.dataprovider.service;

import br.com.btscorreio.cursoteste.api.dataprovider.mapper.CorreioMapperPattern;
import br.com.btscorreio.cursoteste.api.dataprovider.service.exception.ErrorComunicationApiCorreioException;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.utils.builder.CorreioDomainResponseMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * <p>
 *     Método responsável por validar os cenários de teste
 *     da classe de transformação dos dados do parceiro.
 *
 *     <b>CAMADA INTEGRACAO</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
public class CorreioServiceTest {

    private CorreioService correioService;
    private RestTemplate restTemplate;
    private MockRestServiceServer server;
    private CorreioMapperPattern correioMapperPattern;
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        restTemplate = new RestTemplate();
        correioMapperPattern = new CorreioMapperPattern();
        server = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).bufferContent().build();

        correioService = new CorreioService(restTemplate, correioMapperPattern);
    }

    /**
     * Método responsável por validar o cenário de sucesso
     * quando busca os dados no parceiro 'CORREIO' e tem o retorno.
     */
    @Test
    public void testGetDataCorreioSuccess() throws JsonProcessingException {
        server.expect(ExpectedCount.once(),
                requestTo("https://viacep.com.br/ws/06766200/json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(mapper.writeValueAsString(CorreioDomainResponseMock.getMockCorreio()), APPLICATION_JSON));

        CorreioDomainResponse correioDomainResponse = correioService.getDataCorreio("06766200");
        server.verify();

        assertAll(
                () -> assertEquals("06766200", correioDomainResponse.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", correioDomainResponse.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", correioDomainResponse.getBairro()),
                () -> assertEquals("Taboão da Serra", correioDomainResponse.getLocalidade()),
                () -> assertEquals("SP", correioDomainResponse.getUf())
        );
    }

    /**
     * Método responsável por validar o cenário com erro
     * quando busca os dados no parceiro 'CORREIO' e alggum
     * retorno diferente de ok.
     */
    @Test
    public void testGetDataCorreioError() throws JsonProcessingException {
        server.expect(ExpectedCount.once(),
                        requestTo("https://viacep.com.br/ws/06766200/json"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        assertThrows(ErrorComunicationApiCorreioException.class, () -> correioService.getDataCorreio("06766200"));
    }
}
