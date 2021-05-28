package br.ucsal.pdm.unebrasil.model;

public class DoacaoBuilder {

    private static final String DEFAULT_DOADOR = "Talita";
    private static final String DEFAULT_DATA = "02/01/2021";
    private static final String DEFAULT_TIPO = "Cesta Básica";
    private static final String DEFAULT_QUANTIDADE = "1";
    private static final String DEFAULT_BENEFICIARIO = "Luís Antonio";

    private String doador = DEFAULT_DOADOR;
    private String data = DEFAULT_DATA;
    private String tipo = DEFAULT_TIPO;
    private String quantidade = DEFAULT_QUANTIDADE;
    private String beneficiario = DEFAULT_BENEFICIARIO;

    private DoacaoBuilder() {
    }

    public static DoacaoBuilder novaDoacao() {
        return new DoacaoBuilder();
    }

    public DoacaoBuilder doDoador(String doador) {
        this.doador = doador;
        return this;
    }

    public DoacaoBuilder noDia(String data) {
        this.data = data;
        return this;
    }

    public DoacaoBuilder doTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public DoacaoBuilder comQtd(String quantidade) {
        this.quantidade = quantidade;
        return this;
    }

    public DoacaoBuilder paraBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
        return this;
    }

    public DoacaoBuilder mas() {
        return new DoacaoBuilder().noDia(data).doTipo(tipo).comQtd(quantidade).doDoador(doador).paraBeneficiario(beneficiario);
    }

    public Doacao build() {
        Doacao doacao = new Doacao();
        doacao.setDataDoacao(data);
        doacao.setTipoDoacao(tipo);
        doacao.setQtdDoacao(quantidade);
        doacao.setDoador(doador);
        doacao.setBeneficiario(beneficiario);
        return doacao;
    }
}