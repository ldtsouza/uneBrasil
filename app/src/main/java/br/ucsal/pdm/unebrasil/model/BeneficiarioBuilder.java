package br.ucsal.pdm.unebrasil.model;

public class BeneficiarioBuilder {

    private static final String DEFAULT_CPF = null;
    private static final String DEFAULT_NOME = null;
    private static final String DEFAULT_EMAIL = null;
    private static final String DEFAULT_CELULAR = null;
    private static final String DEFAULT_SENHA = null;
    private static final String DEFAULT_DATA = null;

    private String cpf = DEFAULT_CPF;
    private String nome = DEFAULT_NOME;
    private String email = DEFAULT_EMAIL;
    private String celular = DEFAULT_CELULAR;
    private String senha = DEFAULT_SENHA;
    private String data = DEFAULT_DATA;

    private BeneficiarioBuilder() {
    }

    public static BeneficiarioBuilder novoBeneficiario() {
        return new BeneficiarioBuilder();
    }

    public BeneficiarioBuilder comCPF(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public BeneficiarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public BeneficiarioBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public BeneficiarioBuilder comCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public BeneficiarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public BeneficiarioBuilder inscritoNaData(String data) {
        this.data = data;
        return this;
    }

    public BeneficiarioBuilder mas() {
        return new BeneficiarioBuilder().comCPF(cpf).comNome(nome).comEmail(email).comCelular(celular).comSenha(senha).inscritoNaData(data);
    }

    public Beneficiario build() {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setCpf(cpf);
        beneficiario.setNome(nome);
        beneficiario.setEmail(email);
        beneficiario.setCelular(celular);
        beneficiario.setSenha(senha);
        beneficiario.setData(data);
        return beneficiario;
    }
}