package br.ucsal.pdm.unebrasil.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.RepositorioDoador;

public class DoadorViewModel extends AndroidViewModel {

    private RepositorioDoador repositorioDoador;
    private LiveData<List<Doador>> doadores;

    public DoadorViewModel(@NonNull Application application) {
        super(application);
        repositorioDoador = new RepositorioDoador(application);
        doadores = repositorioDoador.getDoadoresAll();
    }

    public LiveData<List<Doador>> getDoadoresAll() {
        return doadores;
    }

    public void inserir(Doador doador) {
        repositorioDoador.insert(doador);
    }

    public void atualizar(Doador doador) {
        repositorioDoador.upgrade(doador);
    }

    public void deletar(Doador doador) {
        repositorioDoador.delete(doador);
    }
}
