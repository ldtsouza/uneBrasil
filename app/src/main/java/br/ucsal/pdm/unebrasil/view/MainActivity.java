package br.ucsal.pdm.unebrasil.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.BancoDados;
import br.ucsal.pdm.unebrasil.repository.dao.DoadorDAO;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private TextView tViewCPF;
    private TextView tViewDoador;
    private TextView tViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacaoDoAdapter();
        efetuarNovaDoacao();
        inicializacaoDosCampos();
        configurarDadosDoador();
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

    public void inicializacaoDosCampos() {
        tViewDoador = findViewById(R.id.main_doador_nome);
        tViewData = findViewById(R.id.main_doador_inscrito);
    }

    public void configurarDadosDoador() {
        String nome = getIntent().getStringExtra("nome");
        String data = getIntent().getStringExtra("data");
        tViewDoador.setText(nome);
        tViewData.setText(data);

    }
}