package br.ucsal.pdm.unebrasil.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.ucsal.pdm.unebrasil.api.BeneficiarioService;
import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.repository.dao.BeneficiarioDAO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositorioBeneficiario {

    private BeneficiarioDAO dao;
    private BeneficiarioService api;
    private LiveData<List<Beneficiario>> beneficiarios;

    public RepositorioBeneficiario(Context context) {
        BancoDados bancoDados = BancoDados.getInstance(context);
        dao = bancoDados.getBeneficiarioDao();
        beneficiarios = dao.obterTodos();
        api = BeneficiarioService.create();
        Call<List<Beneficiario>> listCall = api.listBeneficiario("jaosantos-ba");
        listCall.enqueue(new Callback<List<Beneficiario>>() {
            @Override
            public void onResponse(Call<List<Beneficiario>> call, Response<List<Beneficiario>> response) {
                List<Beneficiario> beneficiariosRecebidos = response.body();
                BancoDados.databaseWriteExecutor.execute(() -> {
                    dao.insere(beneficiariosRecebidos.toArray(new Beneficiario[beneficiariosRecebidos.size()]));
                });
            }

            @Override
            public void onFailure(Call<List<Beneficiario>> call, Throwable t) {
                Log.e("Falhou!", "Erro ao usar a API");
            }
        });
    }

    public LiveData<List<Beneficiario>> getBeneficiariosAll() {
        return beneficiarios;
    }

    public void insert(Beneficiario beneficiario) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.insere(beneficiario);
        });
    }

    public void upgrade(Beneficiario beneficiario) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.atualize(beneficiario);
        });
    }

    public void delete(Beneficiario beneficiario) {
        BancoDados.databaseWriteExecutor.execute(() -> {
            dao.delete(beneficiario);
        });
    }
}
