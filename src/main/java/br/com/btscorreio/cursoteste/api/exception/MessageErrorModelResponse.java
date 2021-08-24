package br.com.btscorreio.cursoteste.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

/**
 * <p>
 *     Classe responsável por mapear os campos com os erros.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MessageErrorModelResponse {

    private String codigo;
    private String mensagem;
    private String url;
}
