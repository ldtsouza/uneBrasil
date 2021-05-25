package br.ucsal.pdm.unebrasil.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.repository.RepositorioBeneficiario;
import br.ucsal.pdm.unebrasil.repository.RepositorioDoacao;

public class MainViewModel extends AndroidViewModel {

    private RepositorioBeneficiario repositorioBeneficiario;
    private RepositorioDoacao repositorioDoacao;

    private LiveData<List<Beneficiario>> beneficiarios;
    private LiveData<List<Doacao>> doacoes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repositorioDoacao = new RepositorioDoacao(application);
        doacoes = repositorioDoacao.getDoacoesAll();
    }

    public LiveData<List<Doacao>> getDoacoes() {
        return doacoes;
    }

}