package br.com.btscorreio.cursoteste.core.usecase;

import br.com.btscorreio.cursoteste.core.exception.NoContentDataCorreioException;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.core.gateway.CorreioGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *     Classe responsável por mapear os casos de uso da jornada da busca do correio
 *     com relação ao CEP para obter os dados.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@AllArgsConstructor
@Component
public class CorreioUseCase extends CorreioUseCaseTemplateMethod {

    private CorreioGateway correioGateway;

    public CorreioDomainResponse getDataCorreio(String zipcode) {
        this.validateFormatZipcode(zipcode);
        CorreioDomainResponse correioDomainResponse = correioGateway.getDataCorreio(zipcode);
        validateReturnPatternZipcode(correioDomainResponse);

        return correioDomainResponse;
    }

    @Override
    void validateFormatZipcode(String zipcode) {
        Pattern pattern = Pattern.compile("^([0-9]{2}.[0-9]{3}-[0-9]{3})|([0-9]{8})|[0-9]{5}-[0-9]{3}$");
        Matcher matcherZipcode = pattern.matcher(zipcode);
        this.validationDiferentRegexZipcode(matcherZipcode);
    }

    /**
     * Método responsável por validar o retorno do conteúdo da resposta do parceiro. Caso a resposta
     * esteja com um corpo vazio é lançado uma exceção com o tipo <b>NoContentDataCorreioException - 204</b>.
     *
     * @param correioDomainResponse {@code CorreioModelPattern}
     */
    private void validateReturnPatternZipcode(CorreioDomainResponse correioDomainResponse) {
        if (StringUtils.isBlank(correioDomainResponse.getCep())) {
            throw new NoContentDataCorreioException();
        }
    }
}
