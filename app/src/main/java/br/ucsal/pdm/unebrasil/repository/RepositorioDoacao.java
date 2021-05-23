package br.ucsal.pdm.unebrasil.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.repository.dao.DoacaoDAO;

public class RepositorioDoacao {

    private DoacaoDAO dao;
    private LiveData<List<Doacao>> doacoes;

    public RepositorioDoacao(Context context) {
        BancoDados bancoDados = BancoDados.getInstance(context);
        dao = bancoDados.getDoacaoDao();
        doacoes = dao.obterTodos();
    }

    public LiveData<List<Doacao>> getDoacoesAll() {
        return doacoes;
    }

    public void insert(Doacao doacao) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.insere(doacao);
        });
    }

    public void upgrade(Doacao doacao) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.atualize(doacao);
        });
    }

    public void delete(Doacao doacao) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.delete(doacao);
        });
    }
}
