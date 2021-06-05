package br.ucsal.pdm.unebrasil.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.dao.DoadorDAO;

public class RepositorioDoador {

    private DoadorDAO dao;
    private LiveData<List<Doador>> doadores;

    public RepositorioDoador(Context context) {
        BancoDados bancoDados = BancoDados.getInstance(context);
        dao = bancoDados.getDoadorDao();
        doadores = dao.obterTodos();
    }

    public LiveData<List<Doador>> getDoadoresAll() {
        return doadores;
    }

    public void insert(Doador doador) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.insere(doador);
        });
    }

    public void upgrade(Doador doador) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.atualize(doador);
        });
    }

    public void delete(Doador doador) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.delete(doador);
        });
    }
}
