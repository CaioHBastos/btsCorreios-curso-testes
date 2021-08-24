package br.com.btscorreio.cursoteste.api.dataprovider.mapper;

import br.com.btscorreio.cursoteste.api.dataprovider.model.response.CorreioModelPattern;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.utils.builder.CorreioModelPatternMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 *     Método responsável por validar os cenários de teste
 *     da classe de transformação dos dados do parceiro.
 *
 *     <b>CAMADA UNIDADE</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@ExtendWith(MockitoExtension.class)
public class CorreioMapperPatternTest {

    @Spy
    private CorreioMapperPattern correioMapperPattern;

    /**
     * Método responsável por validar o cenário de teste de transformação
     * de dados de modelo do parceiro para dominio da aplicação.
     */
    @Test
    public void testToDomainResponseSuccess() {
        CorreioModelPattern correioModelPattern = CorreioModelPatternMock.getMockCorreio();
        CorreioDomainResponse correioDomainResponse = correioMapperPattern.toDomainResponse(correioModelPattern);

        assertAll(
                () -> assertEquals("06766200", correioDomainResponse.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", correioDomainResponse.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", correioDomainResponse.getBairro()),
                () -> assertEquals("Taboão da Serra", correioDomainResponse.getLocalidade()),
                () -> assertEquals("SP", correioDomainResponse.getUf())
        );
    }
}
