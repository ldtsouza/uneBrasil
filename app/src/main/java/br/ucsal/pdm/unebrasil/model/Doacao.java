package br.ucsal.pdm.unebrasil.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Doacao {

    @PrimaryKey (autoGenerate = true)
    private int id;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "tipo")
    private String tipo;

    @ColumnInfo(name = "quantidade")
    private Integer quantidade;

    @ColumnInfo(name = "doador")
    private Doador doador;


    public Doacao (String data, String tipo, Integer quantidade, Doador doador) {
        this.data = data;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.doador = doador;
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getData () {
        return data;
    }

    public void setData (String data) {
        this.data = data;
    }

    public String getTipo () {
        return tipo;
    }

    public void setTipo (String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade () {
        return quantidade;
    }

    public void setQuantidade (Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Doador getDoador () {
        return doador;
    }

    public void setDoador (Doador doador) {
        this.doador = doador;
    }






}
