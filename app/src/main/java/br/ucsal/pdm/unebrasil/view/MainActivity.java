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

import java.util.List;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.BancoDados;
import br.ucsal.pdm.unebrasil.repository.dao.DoacaoDAO;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private TextView tViewDoador;
    private TextView tViewData;
    private TextView tViewDoacoes;
    private TextView tViewNivel;

    Doador doadorLogado;
    List<Doacao> listaDoacoes;
    int doacoesRealizadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializacaoDoAdapter();
        efetuarNovaDoacao();
        inicializacaoDosCampos();
        configurarDadosDoador();
        obterQtdDoacoes();
        efetuarCalculoNivel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        configurarDadosDoador();
        obterQtdDoacoes();
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
        Intent intent = new Intent(this, DoacaoActivity.class).putExtra("doador", doadorLogado);
        startActivityForResult(intent, 0);
    }

    public void inicializacaoDosCampos() {
        tViewDoador = findViewById(R.id.main_doador_nome);
        tViewData = findViewById(R.id.main_doador_inscrito);
        tViewDoacoes = findViewById(R.id.main_doador_doacoes);
        tViewNivel = findViewById(R.id.main_doador_nivel);
    }

    public void configurarDadosDoador() {
        doadorLogado = getIntent().getExtras().getParcelable("doador");
        String nome = doadorLogado.getNome();
        String data = doadorLogado.getData();
        tViewDoador.setText(nome);
        tViewData.setText(data);

    }

    public void obterQtdDoacoes() {
        String cpf = doadorLogado.getCpf();
        BancoDados bancoDados = BancoDados.getInstance(getApplicationContext());
        final DoacaoDAO doacaoDAO = bancoDados.getDoacaoDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                listaDoacoes = doacaoDAO.obterDoacoesPorCPF(cpf);
                doacoesRealizadas = listaDoacoes.size();
                tViewDoacoes.setText(String.valueOf(doacoesRealizadas));
                efetuarCalculoNivel();
            }
        }).start();
    }

    public void efetuarCalculoNivel() {
        if ((doacoesRealizadas > 0) && (doacoesRealizadas < 2)) {
            tViewNivel.setText("Iniciante");
        } else if((doacoesRealizadas >= 2) && (doacoesRealizadas <= 4)) {
            tViewNivel.setText("Bronze");
        } else if((doacoesRealizadas >= 5) && (doacoesRealizadas <= 7)) {
            tViewNivel.setText("Prata");
        } else {
            tViewNivel.setText("Ouro");
        }
    }
}