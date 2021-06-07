package br.ucsal.pdm.unebrasil.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;

@Dao
public interface DoacaoDAO {

    @Insert
    void insere(Doacao... doacao);

    @Query("SELECT * FROM Doacao")
    LiveData<List<Doacao>> obterTodos();

    @Query("SELECT * FROM Doacao WHERE doador=(:cpf)")
    List<Doacao> obterDoacoesPorCPF(String cpf);

    @Update
    void atualize(Doacao... doacao);

    @Delete
    void delete(Doacao... doacao);

}