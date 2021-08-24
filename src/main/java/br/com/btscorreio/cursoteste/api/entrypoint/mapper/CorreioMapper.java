package br.com.btscorreio.cursoteste.api.entrypoint.mapper;

import br.com.btscorreio.cursoteste.api.entrypoint.model.response.CorreioModelResponse;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     Classe responsável por mapear os e transforma-lo em dados de
 *     modelo da aplicação.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@Component
public class CorreioMapper {

    /**
     * Método responsável por transformar os dados de dominio da aplicação
     * para os dados de modelo de resposta da aplicação.
     *
     * @param correioDomainResponse {@code CorreioDomainResponse}
     * @return {@code CorreioModelResponse} - retorna um modelo resposta da aplicação.
     */
    public CorreioModelResponse toModelResponse(CorreioDomainResponse correioDomainResponse) {
        return CorreioModelResponse.builder()
                .bairro(correioDomainResponse.getBairro())
                .logradouro(correioDomainResponse.getLogradouro())
                .cep(correioDomainResponse.getCep())
                .complemento(correioDomainResponse.getComplemento())
                .cidade(correioDomainResponse.getLocalidade())
                .uf(correioDomainResponse.getUf())
                .build();
    }
}
