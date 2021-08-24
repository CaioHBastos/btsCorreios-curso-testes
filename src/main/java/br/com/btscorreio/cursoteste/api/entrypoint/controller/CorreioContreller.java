package br.com.btscorreio.cursoteste.api.entrypoint.controller;

import br.com.btscorreio.cursoteste.api.entrypoint.mapper.CorreioMapper;
import br.com.btscorreio.cursoteste.api.entrypoint.model.response.CorreioModelResponse;
import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;
import br.com.btscorreio.cursoteste.core.usecase.CorreioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *     Classe responsável por mapear os dados de entrada da requisição
 *     do endereço.
 * </p>
 *
 * @author Caio Henrique Bastos
 * @since 23/08/2021
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/bts_correios/v1/addresses")
public class CorreioContreller {

    private CorreioUseCase correioUseCase;
    private CorreioMapper correioMapper;

    @GetMapping
    public ResponseEntity<CorreioModelResponse> getByCep(@RequestParam("cep") String zipcode) {
        CorreioDomainResponse correioDomainResponse = correioUseCase.getDataCorreio(zipcode);
        CorreioModelResponse correioModelResponse = correioMapper.toModelResponse(correioDomainResponse);

        return ResponseEntity.ok(correioModelResponse);
    }
}
