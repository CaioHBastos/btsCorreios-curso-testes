package br.com.btscorreio.cursoteste.utils.builder;

import br.com.btscorreio.cursoteste.api.dataprovider.model.response.CorreioModelPattern;

public class CorreioModelPatternBuilder {

    private final CorreioModelPattern correioModelPattern;

    public CorreioModelPatternBuilder() {
        this.correioModelPattern = new CorreioModelPattern();
    }

    protected CorreioModelPatternBuilder comCep(String cep) {
        this.correioModelPattern.setCep(cep);
        return this;
    }

    protected CorreioModelPatternBuilder comLogradouro(String logradouro) {
        this.correioModelPattern.setLogradouro(logradouro);
        return this;
    }

    protected CorreioModelPatternBuilder comComplemento(String complemento) {
        this.correioModelPattern.setComplemento(complemento);
        return this;
    }

    protected CorreioModelPatternBuilder comBairro(String bairro) {
        this.correioModelPattern.setBairro(bairro);
        return this;
    }

    protected CorreioModelPatternBuilder comLocalidade(String localidade) {
        this.correioModelPattern.setLocalidade(localidade);
        return this;
    }

    protected CorreioModelPatternBuilder comUf(String uf) {
        this.correioModelPattern.setUf(uf);
        return this;
    }

    protected CorreioModelPattern criar() {
        return this.correioModelPattern;
    }
}
