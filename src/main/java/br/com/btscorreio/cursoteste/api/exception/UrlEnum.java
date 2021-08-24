package br.com.btscorreio.cursoteste.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public enum UrlEnum {

    OT_FOUND("/registro-nao-encontrado", 404),
    BAD_REQUEST("/campo-invalido-na-requisicao", 400),
    UNPROCESSABLE_ENTITY("/erro-de-negocio", 422),
    INTERNAL_SERVER("/erro-generico", 500);

    private String url;
    private Integer httpStatus;

    public static String getUrl(Integer httpStatusValue) {
        if (Objects.nonNull(httpStatusValue)) {
            for (UrlEnum urlErroEnum : values()) {
                if (urlErroEnum.getHttpStatus().equals(httpStatusValue)) {
                    return urlErroEnum.getUrl();
                }
            }
        }

        return UrlEnum.INTERNAL_SERVER.getUrl();
    }
}
