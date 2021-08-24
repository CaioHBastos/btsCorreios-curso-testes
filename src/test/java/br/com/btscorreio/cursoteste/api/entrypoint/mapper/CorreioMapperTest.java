package br.com.btscorreio.cursoteste.api.entrypoint.mapper;

import br.com.btscorreio.cursoteste.api.entrypoint.model.response.CorreioModelResponse;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.utils.builder.CorreioDomainResponseMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 *     Método responsável por validar os cenários de teste
 *     da classe de transformação dos dados do dominio.
 *
 *     <b>CAMADA UNIDADE</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@ExtendWith(MockitoExtension.class)
public class CorreioMapperTest {

    @Spy
    private CorreioMapper correioMapper;

    /**
     * Método responsável por validar o cenário de teste de transformação
     * de dados de dominio para os dados de modelo de saída da aplicação.
     */
    @Test
    public void testToDomainResponseSuccess() {
        CorreioDomainResponse correioDomainResponse = CorreioDomainResponseMock.getMockCorreio();
        CorreioModelResponse correioModelResponse = correioMapper.toModelResponse(correioDomainResponse);

        assertAll(
                () -> assertEquals("06766200", correioModelResponse.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", correioModelResponse.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", correioModelResponse.getBairro()),
                () -> assertEquals("Taboão da Serra", correioModelResponse.getCidade()),
                () -> assertEquals("SP", correioModelResponse.getUf())
        );
    }
}
