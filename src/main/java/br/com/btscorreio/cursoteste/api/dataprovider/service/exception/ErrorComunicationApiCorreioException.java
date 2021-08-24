package br.com.btscorreio.cursoteste.api.dataprovider.service.exception;

/**
 * <p>
 *     Classe responsável por ser a exception que está relacionado a algum
 *     erro de comunicação com a aplicação do correio.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
public class ErrorComunicationApiCorreioException extends RuntimeException {

    public ErrorComunicationApiCorreioException(String mensage) {
        super(mensage);
    }
}
