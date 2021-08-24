package br.com.btscorreio.cursoteste.api.dataprovider.mapper;

import br.com.btscorreio.cursoteste.api.dataprovider.model.response.CorreioModelPattern;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import org.springframework.stereotype.Component;

/**
 * <p>
 *     Classe responsável por realizar o mapeamento dos dados
 *     no qual transforma os dados do modelo do parceiro
 *     para o modelo de dominio da aplicação.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@Component
public class CorreioMapperPattern {

    /**
     * Método responsável por transformar os dados que retornam da aplicação do correios
     * em dados de dominio da aplicação.
     *
     * @param correioModelPattern {@code CorreioDomainResponse}
     * @return {@code CorreioDomainResponse} - retorna um dado de dominio da aplicação.
     */
    public CorreioDomainResponse toDomainResponse(CorreioModelPattern correioModelPattern) {
        return CorreioDomainResponse.builder()
                .bairro(correioModelPattern.getBairro())
                .cep(correioModelPattern.getCep())
                .complemento(correioModelPattern.getComplemento())
                .localidade(correioModelPattern.getLocalidade())
                .logradouro(correioModelPattern.getLogradouro())
                .uf(correioModelPattern.getUf())
                .build();
    }
}
