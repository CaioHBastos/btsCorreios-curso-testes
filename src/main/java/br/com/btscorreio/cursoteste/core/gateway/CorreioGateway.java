package br.com.btscorreio.cursoteste.core.gateway;

import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;

/**
 * <p>
 *     Interface responsável por ser o contrato da aplicação e obter como retorno
 *     os dados de dominio.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
public interface CorreioGateway {

    /**
     * Método responsável por ser o contrato no qual quem implementar a interface
     * vai assinar para buscar os dados na aplicação do correio.
     *
     * @param zipcode {@code String}
     * @return {@code CorreioDomainModel} - Retorna os dados de dominio do correio.
     */
    CorreioDomainResponse getDataCorreio(String zipcode);
}