package br.com.btscorreio.cursoteste.core.usecase;

import br.com.btscorreio.cursoteste.core.exception.BadBusyRequestException;

import java.util.regex.Matcher;

/**
 * <p>
 *     Classe abstrata responsável por ser extendida pelo filho para implementar
 *     os métodos que serão as validações do caso de uso.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/09/2021
 */
public abstract class CorreioUseCaseTemplateMethod {

    abstract void validateFormatZipcode(String zipcode);

    /**
     * Método responsável por validar se o regex que foi informado está inválido.
     * Caso seja invalido, irá lançar uma exceção de negócio com erro na requisição.
     *
     * @param matcherZipcode {@code Matcher}
     */
    protected void validationDiferentRegexZipcode(Matcher matcherZipcode) {
        if (!matcherZipcode.matches()) {
            throw new BadBusyRequestException("Erro na validação de negócio, o CEP não foi informado de forma correta.");
        }
    }
}