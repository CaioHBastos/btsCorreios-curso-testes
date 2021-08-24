package br.com.btscorreio.cursoteste.api.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

/**
 * <p>
 *     Classe responsável por mapear os dados de saída
 *     da aplicação com relação ao modelo do correio.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CorreioModelResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
}
