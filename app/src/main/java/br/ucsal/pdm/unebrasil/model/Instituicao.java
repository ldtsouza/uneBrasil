package br.ucsal.pdm.unebrasil.model;

public class Instituicao {

    private static final String DEFAULT_RAZAO = "Associação Clara Amizade";
    private static final String DEFAULT_CNPJ = "04.087.181/0001-96";
    private static final String DEFAULT_TELEFONE = "+55 71 3375-5135";
    private static final String DEFAULT_EMAIL = "contato@claraamizade.org.br";
    private static final String DEFAULT_HISTORICO = "A Associação é uma entidade sem fins lucrativos que tem como objetivo a educação global, destinados às crianças, adolescentes e jovens em situação de risco social.";

    private String razao = DEFAULT_RAZAO;
    private String cnpj = DEFAULT_CNPJ;
    private String telefone = DEFAULT_TELEFONE;
    private String email = DEFAULT_EMAIL;
    private String historico = DEFAULT_HISTORICO;

    public Instituicao() {
    }

    public String getRazao() {
        return razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getHistorico() {
        return historico;
    }
}
