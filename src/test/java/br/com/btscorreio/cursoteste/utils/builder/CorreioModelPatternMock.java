package br.com.btscorreio.cursoteste.utils.builder;

import br.com.btscorreio.cursoteste.api.dataprovider.model.response.CorreioModelPattern;
import org.springframework.http.ResponseEntity;

public class CorreioModelPatternMock {

    public static CorreioModelPattern getMockCorreio() {
        return new CorreioModelPatternBuilder()
                .comCep("06766200")
                .comLogradouro("Rua Doutor Carlos Siqueira Neto")
                .comBairro("Pq Pinheiros")
                .comLocalidade("Taboão da Serra")
                .comUf("SP")
                .criar();
    }

    public static ResponseEntity<CorreioModelPattern> getMockCorreioResponseEntity() {
        CorreioModelPattern correioModelPattern = new CorreioModelPatternBuilder()
                .comCep("06766200")
                .comLogradouro("Rua Doutor Carlos Siqueira Neto")
                .comBairro("Pq Pinheiros")
                .comLocalidade("Taboão da Serra")
                .comUf("SP")
                .criar();

        return ResponseEntity.ok(correioModelPattern);
    }
}
