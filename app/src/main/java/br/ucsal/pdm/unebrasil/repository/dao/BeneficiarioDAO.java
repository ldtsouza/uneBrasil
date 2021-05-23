package br.ucsal.pdm.unebrasil.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Beneficiario;

@Dao
public interface BeneficiarioDAO {

    @Insert
    void insere(Beneficiario... beneficiario);

    @Query("SELECT * FROM Beneficiario")
    LiveData<List<Beneficiario>> obterTodos();

    @Update
    void atualize(Beneficiario... beneficiario);

    @Delete
    void delete(Beneficiario... beneficiario);

}