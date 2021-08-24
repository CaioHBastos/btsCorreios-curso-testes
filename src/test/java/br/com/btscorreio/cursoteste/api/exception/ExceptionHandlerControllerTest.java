package br.com.btscorreio.cursoteste.api.exception;

import br.com.btscorreio.cursoteste.api.dataprovider.service.exception.ErrorComunicationApiCorreioException;
import br.com.btscorreio.cursoteste.core.exception.BadBusyRequestException;
import br.com.btscorreio.cursoteste.core.exception.NoContentDataCorreioException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 *     Classe responsável por validar os cenários de testes
 *     da classe que controla as exceções que ocorre na aplicação.
 *
 *     <b>CAMADA UNIDADE</b>
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@SpringJUnitConfig
@WebMvcTest(ExceptionHandlerController.class)
public class ExceptionHandlerControllerTest {

    @MockBean
    ExceptionHandlerController exceptionHandlerController;

    @Test
    public void testHandleInternalServer() {
        ErrorComunicationApiCorreioException errorComunicationApiCorreioException = new ErrorComunicationApiCorreioException("Erro na comunicação");
        ResponseEntity<MessageErrorModelResponse> responseEntityMessage = exceptionHandlerController.handleInternalServer(errorComunicationApiCorreioException);

        assertAll(
                () -> assertEquals(500, responseEntityMessage.getStatusCodeValue()),
                () -> assertEquals("500", responseEntityMessage.getBody().getCodigo()),
                () -> assertEquals("Erro na comunicação", responseEntityMessage.getBody().getMensagem()),
                () -> assertEquals("/erro-generico", responseEntityMessage.getBody().getUrl())
        );
    }

    @Test
    public void testHandleNoContent() {
        NoContentDataCorreioException noContentDataCorreioException = new NoContentDataCorreioException();
        ResponseEntity<?> responseEntityMessage = exceptionHandlerController.handleNoContent(noContentDataCorreioException);

        assertAll(
                () -> assertEquals(204, responseEntityMessage.getStatusCodeValue())
        );
    }

    @Test
    public void testHandleBuseException() {
        BadBusyRequestException badBusyRequestException = new BadBusyRequestException("Erro de negócio");
        ResponseEntity<MessageErrorModelResponse> responseEntityMessage = exceptionHandlerController.handleBusyException(badBusyRequestException);

        assertAll(
                () -> assertEquals(422, responseEntityMessage.getStatusCodeValue()),
                () -> assertEquals("422", responseEntityMessage.getBody().getCodigo()),
                () -> assertEquals("Erro de negócio", responseEntityMessage.getBody().getMensagem()),
                () -> assertEquals("/erro-de-negocio", responseEntityMessage.getBody().getUrl())
        );
    }
}
