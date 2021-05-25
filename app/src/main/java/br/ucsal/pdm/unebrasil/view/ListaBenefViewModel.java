package br.ucsal.pdm.unebrasil.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.repository.RepositorioBeneficiario;

public class ListaBenefViewModel extends AndroidViewModel {
    private RepositorioBeneficiario repositorioBeneficiario;

    private LiveData<List<Beneficiario>> beneficiarios;

    public ListaBenefViewModel(@NonNull Application application) {
        super(application);
        repositorioBeneficiario = new RepositorioBeneficiario(application);
        beneficiarios = repositorioBeneficiario.getBeneficiariosAll();
    }

    public LiveData<List<Beneficiario>> getBeneficiarios() {
        return beneficiarios;
    }

    public void inserir(Beneficiario beneficiario) {
        repositorioBeneficiario.insert(beneficiario);
    }

    public void excluir(Beneficiario beneficiario) {
        repositorioBeneficiario.delete(beneficiario);
    }
}