package br.com.btscorreio.cursoteste.api.dataprovider.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *     Classe responsável por modelar a resposta do parceiro com a busca
 *     dos dados do correio.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CorreioModelPattern {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
