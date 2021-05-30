package br.ucsal.pdm.unebrasil.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.ucsal.pdm.unebrasil.R;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacaoDoAdapter();
        efetuarNovaDoacao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_beneficiarios:
                startActivity(new Intent(this, ListaBenefActivity.class));
                return true;
            case R.id.action_instituicao:
                startActivity(new Intent(this, DadosIntituicaoActivity.class));
                return true;
            case R.id.action_sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void inicializacaoDoAdapter() {
        RecyclerView recyclerView = findViewById(R.id.main_doador_recyclerview);
        MainCustomAdapter mainCustomAdapter = new MainCustomAdapter(new MainCustomAdapter.DoacaoDiff());
        recyclerView.setAdapter(mainCustomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));

        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainViewModel.class);

        viewModel.getDoacoes().observe(this, doacoes -> {
            mainCustomAdapter.submitList(doacoes);
        });
    }

    private void efetuarNovaDoacao() {
        FloatingActionButton botaoNovaDoacao = findViewById(R.id.main_doador_botao);
        botaoNovaDoacao.setOnClickListener(view -> abreFomularioAdicionarDoacao());
    }

    public void abreFomularioAdicionarDoacao() {
        startActivity(new Intent(this, DoacaoActivity.class));
    }
}