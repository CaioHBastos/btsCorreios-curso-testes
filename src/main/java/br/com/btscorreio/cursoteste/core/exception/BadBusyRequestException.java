package br.com.btscorreio.cursoteste.core.exception;

/**
 * <p>
 *     Classe responsável por tratar os valores do campo de entrada
 *     na requisição.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
public class BadBusyRequestException extends RuntimeException {

    public BadBusyRequestException(String mensage) {
        super(mensage);
    }
}
