package br.com.btscorreio.cursoteste.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     Classe responsável por realizar a configuração da chamada para aplicações externa
 *     no contexto rest client da aplicação.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23.08.2021
 */
@Configuration
public class RestClientConfiguration {

    @Bean
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors = restTemplate.getInterceptors();

        if(CollectionUtils.isEmpty(clientHttpRequestInterceptors)) {
            clientHttpRequestInterceptors = new ArrayList<>();
        }

        restTemplate.setInterceptors(clientHttpRequestInterceptors);

        return restTemplate;
    }
}
