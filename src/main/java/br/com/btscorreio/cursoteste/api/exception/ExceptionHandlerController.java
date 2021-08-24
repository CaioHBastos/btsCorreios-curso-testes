package br.com.btscorreio.cursoteste.api.exception;

import br.com.btscorreio.cursoteste.api.dataprovider.service.exception.ErrorComunicationApiCorreioException;
import br.com.btscorreio.cursoteste.core.exception.BadBusyRequestException;
import br.com.btscorreio.cursoteste.core.exception.NoContentDataCorreioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <p>
 *     Classe responsável por capturar as exceçoes que ocorrem na aplicação
 *     e realizar o devido tratamento.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/10/2021
 */
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoContentDataCorreioException.class)
    public final ResponseEntity<?> handleNoContent(Exception exception) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler({ErrorComunicationApiCorreioException.class, Exception.class })
    public final ResponseEntity<MessageErrorModelResponse> handleInternalServer(Exception exception) {
        MessageErrorModelResponse mensagemErrorModelResponse = MessageErrorModelResponse.builder()
                .codigo(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .mensagem(exception.getMessage())
                .url(UrlEnum.getUrl(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .build();

        return new ResponseEntity<>(mensagemErrorModelResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ BadBusyRequestException.class })
    public final ResponseEntity<MessageErrorModelResponse> handleBusyException(Exception exception) {
        MessageErrorModelResponse mensagemErrorModelResponse = MessageErrorModelResponse.builder()
                .codigo(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .mensagem(exception.getMessage())
                .url(UrlEnum.getUrl(HttpStatus.UNPROCESSABLE_ENTITY.value()))
                .build();

        return new ResponseEntity<>(mensagemErrorModelResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
