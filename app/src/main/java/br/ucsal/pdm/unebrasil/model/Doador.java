package br.ucsal.pdm.unebrasil.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Doador implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String cpf;

    private String nome;

    private String email;

    private String celular;

    private String senha;

    private String data;

    public Doador() {
    }

    @Ignore
    public Doador(String cpf, String nome, String email, String celular, String senha, String data) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.senha = senha;
        this.data = "01/06/2021";
    }

    protected Doador(Parcel in) {
        id = in.readInt();
        cpf = in.readString();
        nome = in.readString();
        email = in.readString();
        celular = in.readString();
        senha = in.readString();
        data = in.readString();
    }

    public static final Creator<Doador> CREATOR = new Creator<Doador>() {
        @Override
        public Doador createFromParcel(Parcel in) {
            return new Doador(in);
        }

        @Override
        public Doador[] newArray(int size) {
            return new Doador[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doador doador = (Doador) o;
        return id == doador.id &&
                Objects.equals(cpf, doador.cpf) &&
                Objects.equals(nome, doador.nome) &&
                Objects.equals(email, doador.email) &&
                Objects.equals(celular, doador.celular) &&
                Objects.equals(senha, doador.senha) &&
                Objects.equals(data, doador.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, nome, email, celular, senha, data);
    }

    @Override
    public String toString() {
        return "Doador{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", senha='" + senha + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public boolean temIdValido() {
        return id > 0;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(cpf);
        parcel.writeString(nome);
        parcel.writeString(email);
        parcel.writeString(celular);
        parcel.writeString(senha);
        parcel.writeString(data);
    }
}