package br.ucsal.pdm.unebrasil.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doador;

@Dao
public interface DoadorDAO {
    @Insert
    void insere(Doador... doador);

    @Query("SELECT * FROM doador")
    LiveData<List<Doador>> obterLista();

    @Update
    void atualize(Doador... doador);

    @Delete
    void excluir(Doador... doador);




}
