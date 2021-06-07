package br.ucsal.pdm.unebrasil.repository.dao;

import android.os.Parcelable;

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

    @Query("SELECT * from Doador")
    LiveData<List<Doador>> obterTodos();

    @Query("SELECT * from Doador where cpf=(:cpf) and senha=(:senha)")
    Doador autenticar(String cpf, String senha);

    @Update
    void atualize(Doador... doador);

    @Delete
    void delete(Doador... doador);

}