package br.ucsal.pdm.unebrasil.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.ucsal.pdm.unebrasil.R;

public class ListaBenefActivity extends AppCompatActivity {

    private ListaBenefViewModel benefViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_beneficiario);
        inicializacaoDoAdapter();
    }

    public void inicializacaoDoAdapter() {
        RecyclerView recyclerView = findViewById(R.id.benef_recyclerview);
        ListaBenefCustomAdapter listaBenefCustomAdapter = new ListaBenefCustomAdapter(new ListaBenefCustomAdapter.BeneficiarioDiff());
        recyclerView.setAdapter(listaBenefCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        benefViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(ListaBenefViewModel.class);

        benefViewModel.getBeneficiarios().observe(this, beneficiarios -> {
            listaBenefCustomAdapter.submitList(beneficiarios);
        });
    }
}
