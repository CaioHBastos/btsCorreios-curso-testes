package br.com.btscorreio.cursoteste.utils.builder;

import br.com.btscorreio.cursoteste.core.domain.response.CorreioDomainResponse;

public class CorreioDomainResponseMock {

    public static CorreioDomainResponse getMockCorreio() {
        return CorreioDomainResponse.builder()
                .cep("06766200")
                .logradouro("Rua Doutor Carlos Siqueira Neto")
                .bairro("Pq Pinheiros")
                .localidade("Taboão da Serra")
                .uf("SP")
                .build();
    }

    public static CorreioDomainResponse getMockCorreioErroBusca() {
        return CorreioDomainResponse.builder().build();
    }
}
