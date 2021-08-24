package br.com.btscorreio.cursoteste.core.usecase;

import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.core.exception.BadBusyRequestException;
import br.com.btscorreio.cursoteste.core.exception.NoContentDataCorreioException;
import br.com.btscorreio.cursoteste.core.gateway.CorreioGateway;
import br.com.btscorreio.cursoteste.utils.builder.CorreioDomainResponseMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>
 *     Método responsável por validar os cenários da regra de negócio
 *     com os dados mocado do parceiro.
 *
 *     <b>CAMADA UNIDADE</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@ExtendWith(SpringExtension.class)
public class CorreioUseCaseTest {

    private static final String ZIPCODE = "06766200";
    private static final String ZIPCODE_FORMAT = "06.766-200";
    private static final String ZIPCODE_FORMAT_OTHER = "06766-200";

    @InjectMocks
    private CorreioUseCase correioUseCase;

    @Mock
    private CorreioGateway correioGateway;

    /**
     * Método responsável por validar o cenário de teste
     * quando o CEP é informado sem formatação.
     */
    @Test
    public void testGetDataCorreioSuccessZipcodeWithoutFormat() {
        BDDMockito.given(correioGateway.getDataCorreio(ZIPCODE)).willReturn(CorreioDomainResponseMock.getMockCorreio());

        CorreioDomainResponse dataCorreioDomain = correioUseCase.getDataCorreio(ZIPCODE);

        assertAll(
                () -> assertEquals("06766200", dataCorreioDomain.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", dataCorreioDomain.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", dataCorreioDomain.getBairro()),
                () -> assertEquals("Taboão da Serra", dataCorreioDomain.getLocalidade()),
                () -> assertEquals("SP", dataCorreioDomain.getUf())
        );
    }

    /**
     * Método responsável por validar o cenário de teste
     * quando o CEP é informado com um tipo de formatação.
     */
    @Test
    public void testGetDataCorreioSuccessZipcodeWithFormat() {
        BDDMockito.given(correioGateway.getDataCorreio(ZIPCODE_FORMAT)).willReturn(CorreioDomainResponseMock.getMockCorreio());

        CorreioDomainResponse dataCorreioDomain = correioUseCase.getDataCorreio(ZIPCODE_FORMAT);

        assertAll(
                () -> assertEquals("06766200", dataCorreioDomain.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", dataCorreioDomain.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", dataCorreioDomain.getBairro()),
                () -> assertEquals("Taboão da Serra", dataCorreioDomain.getLocalidade()),
                () -> assertEquals("SP", dataCorreioDomain.getUf())
        );
    }

    /**
     * Método responsável por validar o cenário de teste
     * quando o cep é informado com outro tipo de formatação.
     */
    @Test
    public void testGetDataCorreioSuccessZipcodeWithFormatOther() {
        BDDMockito.given(correioGateway.getDataCorreio(ZIPCODE_FORMAT_OTHER)).willReturn(CorreioDomainResponseMock.getMockCorreio());

        CorreioDomainResponse dataCorreioDomain = correioUseCase.getDataCorreio(ZIPCODE_FORMAT_OTHER);

        assertAll(
                () -> assertEquals("06766200", dataCorreioDomain.getCep()),
                () -> assertEquals("Rua Doutor Carlos Siqueira Neto", dataCorreioDomain.getLogradouro()),
                () -> assertEquals("Pq Pinheiros", dataCorreioDomain.getBairro()),
                () -> assertEquals("Taboão da Serra", dataCorreioDomain.getLocalidade()),
                () -> assertEquals("SP", dataCorreioDomain.getUf())
        );
    }

    /**
     * Método responsável por validar o cenário de teste
     * quando cep é informado de forma inválida e lança uma
     * exceção de negócio.
     */
    @Test
    public void testGetDataCorreioBadBusyException() {
        BDDMockito.given(correioGateway.getDataCorreio("0676620"))
                .willThrow(new BadBusyRequestException("Erro na validação de negócio, o CEP não foi informado de forma correta."));

        assertThrows(BadBusyRequestException.class, () -> correioUseCase.getDataCorreio("0676620"));
    }

    /**
     * Método responsável por validar o cenário de teste
     * quando o parceiro tem erro no retorno
     * e lança uma exceção de sem conteúdo.
     */
    @Test
    public void testGetDataCorreioNoContentException() {
        BDDMockito.given(correioGateway.getDataCorreio(ZIPCODE))
                .willReturn(CorreioDomainResponseMock.getMockCorreioErroBusca())
                .willThrow(new NoContentDataCorreioException());

        assertThrows(NoContentDataCorreioException.class, () -> correioUseCase.getDataCorreio(ZIPCODE));
    }
}
