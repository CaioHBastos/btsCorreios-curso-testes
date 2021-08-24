package br.com.btscorreio.cursoteste.api.dataprovider.service;

import br.com.btscorreio.cursoteste.api.dataprovider.mapper.CorreioMapperPattern;
import br.com.btscorreio.cursoteste.api.dataprovider.model.response.CorreioModelPattern;
import br.com.btscorreio.cursoteste.api.dataprovider.service.exception.ErrorComunicationApiCorreioException;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.core.gateway.CorreioGateway;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Classe responsável por realizar o serviço de comunicação com a aplicação do correio
 * e integrar para obter os dados de retorno da pesquisa pelo CEP.
 */
@AllArgsConstructor
@Service
public class CorreioService implements CorreioGateway {

    private static final String PATH = "https://viacep.com.br/ws/";
    private static final String TIPO_BUSCA = "/json";

    private RestTemplate restTemplate;
    private CorreioMapperPattern correioMapperPattern;

    @Override
    public CorreioDomainResponse getDataCorreio(String zipcode) {

        String url = montarUrl(zipcode);
        ResponseEntity<CorreioModelPattern> correioModelPattern = realizarComunicaoApiCorreio(url);
        validarRetornoDiferenteDeOk(correioModelPattern.getStatusCode());

        return correioMapperPattern.toDomainResponse(Objects.requireNonNull(correioModelPattern.getBody()));
    }

    /**
     * Método responsável por montar a url da API
     * do correio, para buscar os dados como JSON.
     *
     * @param cep {@code String}
     * @return {@code String} retorna uma URL montada.
     */
    private String montarUrl(String cep) {
        return PATH.concat(cep).concat(TIPO_BUSCA);
    }

    /**
     * Método responsável por realizar a comunicação com a aplicação dos correios através de uma chamada
     * restTemplate, com o método GET.
     *
     * @param url {@code String}
     * @return {@code ResponseEntity<CorreioModelPattern} - Retornar uma entidade de resposta da aplicação
     *      do correio.
     */
    private ResponseEntity<CorreioModelPattern> realizarComunicaoApiCorreio(String url) {
        ResponseEntity<CorreioModelPattern> responseEntityCorreio;

        try {
            responseEntityCorreio = this.restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        } catch (HttpServerErrorException exception) {
            throw new ErrorComunicationApiCorreioException("Houve um erro na comunicação com a aplicação do correio.");
        }

        return responseEntityCorreio;
    }

    /**
     * Método responsável por validar se o retorno da aplicação
     * do correio é diferente de OK <b>200</b>
     *
     * @param statusCode {@code HttpStatus}
     */
    private void validarRetornoDiferenteDeOk(HttpStatus statusCode) {
        if (!statusCode.equals(HttpStatus.OK)) {
            throw new ErrorComunicationApiCorreioException("Houve um erro na comunicação com a aplicação do correio.");
        }
    }
}
