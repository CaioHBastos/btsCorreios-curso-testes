package br.com.btscorreio.cursoteste.core.domain.response;

import lombok.Builder;
import lombok.Getter;

/**
 * <p>
 *     Classe responsável por modelar os dados de dominio da aplicação.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 *
 */
@Getter
@Builder
public class CorreioDomainResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}