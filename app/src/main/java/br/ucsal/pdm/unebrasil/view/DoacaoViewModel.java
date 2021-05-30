package br.ucsal.pdm.unebrasil.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.repository.RepositorioDoacao;

public class DoacaoViewModel extends AndroidViewModel {

    private RepositorioDoacao repositorioDoacao;
    private LiveData<List<Doacao>> doacoes;

    public DoacaoViewModel(@NonNull Application application) {
        super(application);
        repositorioDoacao = new RepositorioDoacao(application);
        doacoes = repositorioDoacao.getDoacoesAll();
    }

    public LiveData<List<Doacao>> getDoacoes() {
        return doacoes;
    }

    public void inserir(Doacao doacao) {
        repositorioDoacao.insert(doacao);
    }

    public void atualizar(Doacao doacao) {
        repositorioDoacao.upgrade(doacao);
    }

    public void deletar(Doacao doacao) {
        repositorioDoacao.delete(doacao);
    }
}