package br.ucsal.pdm.unebrasil.api;

import java.util.List;

import br.ucsal.pdm.unebrasil.model.Beneficiario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BeneficiarioService {

    @GET("{user}/api_beneficiario/main/api_benef.json")
    Call<List<Beneficiario>> listBeneficiario(@Path("user") String user);

    static BeneficiarioService create() {
        return new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(BeneficiarioService.class);
    }
}