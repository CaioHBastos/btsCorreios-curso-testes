package br.com.btscorreio.cursoteste.core.exception;

/**
 * <p>
 *     Classe responsável por representar a exceção quando a
 *     a busca dos dados do correio, retorna com sucesso
 *     mas não tem conteúdo.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
public class NoContentDataCorreioException extends RuntimeException {

    public NoContentDataCorreioException() {

    }
}
