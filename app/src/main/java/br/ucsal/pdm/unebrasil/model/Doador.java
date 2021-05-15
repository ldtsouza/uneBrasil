package br.ucsal.pdm.unebrasil.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Doador {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="cpf")
    private String cpf;

    @ColumnInfo(name="nome")
    private String nome;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="telefone")
    private String telefone;

    public Doador () {


    }

    public Doador  (String cpf, String nome, String email, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getCpf () {
        return cpf;
    }

    public void setCpf (String cpf) {
        this.cpf = cpf;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getTelefone () {
        return telefone;
    }

    public void setTelefone (String telefone) {
        this.telefone = telefone;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Doador  that = (Doador) o;
        return id == that.id &&
                Objects.equals (cpf, that.cpf) &&
                Objects.equals (nome, that.nome) &&
                Objects.equals (email, that.email) &&
                Objects.equals (telefone, that.telefone);
    }

    @Override
    public int hashCode () {
        return Objects.hash (id, cpf, nome, email, telefone);
    }

    @Override
    public String toString () {
        return "Doador {" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }


}
