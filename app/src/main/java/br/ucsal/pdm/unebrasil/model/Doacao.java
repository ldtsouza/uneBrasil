package br.ucsal.pdm.unebrasil.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Doacao {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String doador;

    private String dataDoacao;

    private String tipoDoacao;

    private String qtdDoacao;

    public Doacao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoador() {
        return doador;
    }

    public void setDoador(String doador) {
        this.doador = doador;
    }

    public String getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(String dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public String getTipoDoacao() {
        return tipoDoacao;
    }

    public void setTipoDoacao(String tipoDoacao) {
        this.tipoDoacao = tipoDoacao;
    }

    public String getQtdDoacao() {
        return qtdDoacao;
    }

    public void setQtdDoacao(String qtdDoacao) {
        this.qtdDoacao = qtdDoacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doacao doacao = (Doacao) o;
        return id == doacao.id &&
                Objects.equals(doador, doacao.doador) &&
                Objects.equals(dataDoacao, doacao.dataDoacao) &&
                Objects.equals(tipoDoacao, doacao.tipoDoacao) &&
                Objects.equals(qtdDoacao, doacao.qtdDoacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doador, dataDoacao, tipoDoacao, qtdDoacao);
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "id=" + id +
                ", doador='" + doador + '\'' +
                ", dataDoacao='" + dataDoacao + '\'' +
                ", tipoDoacao='" + tipoDoacao + '\'' +
                ", qtdDoacao='" + qtdDoacao + '\'' +
                '}';
    }

    public boolean temIdValido() {
        return id > 0;
    }
}