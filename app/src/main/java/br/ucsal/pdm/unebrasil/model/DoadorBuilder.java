package br.ucsal.pdm.unebrasil.model;

public class DoadorBuilder {

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

    private DoadorBuilder() {
    }

    public static DoadorBuilder novoDoador() {
        return new DoadorBuilder();
    }

    public DoadorBuilder comCPF(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public DoadorBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public DoadorBuilder comEmail(String email) {
        this.email = email;
        return this;
    }

    public DoadorBuilder comCelular(String celular) {
        this.celular = celular;
        return this;
    }

    public DoadorBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public DoadorBuilder inscritoNaData(String data) {
        this.data = data;
        return this;
    }

    public DoadorBuilder mas() {
        return new DoadorBuilder().comCPF(cpf).comNome(nome).comEmail(email).comCelular(celular).comSenha(senha).inscritoNaData(data);
    }

    public Doador build() {
        Doador doador = new Doador();
        doador.setCpf(cpf);
        doador.setNome(nome);
        doador.setEmail(email);
        doador.setCelular(celular);
        doador.setSenha(senha);
        doador.setData(data);
        return doador;
    }
}