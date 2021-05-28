package br.ucsal.pdm.unebrasil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.model.DoadorBuilder;

import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOACAO;
import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOADOR;

public class DoadorActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_DOADOR = "Novo Doador";
    private static final String TITULO_APPBAR_EDITA_DOADOR = "Editar Doador";

    private TextInputEditText campoCPF;
    private TextInputEditText campoNome;
    private TextInputEditText campoEmail;
    private TextInputEditText campoCeular;
    private TextInputEditText campoSenha;
    private TextInputEditText campoData;

    private Doador doador;
    private DoadorViewModel doadorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_doador);
        inicializacaoDosCampos();
        carregaDoador();
        inicializacaoDoAdapter();
    }

    public void inicializacaoDosCampos() {
        campoCPF = findViewById(R.id.cad_doador_input_et_cpf);
        campoNome = findViewById(R.id.cad_doador_input_et_nome);
        campoEmail = findViewById(R.id.cad_doador_input_et_email);
        campoCeular = findViewById(R.id.cad_doador_input_et_telefone);
        campoSenha = findViewById(R.id.cad_doador_input_et_senha);
    }

    public void carregaDoador() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_DOADOR)) {
            setTitle(TITULO_APPBAR_EDITA_DOADOR);
            doador = (Doador) dados.getSerializableExtra(CHAVE_DOADOR);
            obterDadosDoador();
        } else {
            setTitle(TITULO_APPBAR_NOVO_DOADOR);
            doador = new Doador();
        }
    }

    private void preencherDadosDoador() {
        DoadorBuilder doadorBuilder = DoadorBuilder.novoDoador();

        doador = doadorBuilder.mas()
                .comCPF(campoCPF.getText().toString())
                .comNome(campoNome.getText().toString())
                .comEmail(campoEmail.getText().toString())
                .comCelular(campoCeular.getText().toString())
                .comSenha(campoSenha.getText().toString())
                .build();
    }

    public void inicializacaoDoAdapter() {
        DoadorCustomAdapter doadorCustomAdapter = new DoadorCustomAdapter(new DoadorCustomAdapter.DoadorDiff());

        doadorViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DoadorViewModel.class);

        doadorViewModel.getDoadoresAll().observe(this, doadores -> {
            doadorCustomAdapter.submitList(doadores);
        });
    }

    public void finalizaFormularioDoador(View v) {
        preencherDadosDoador();
        if(doador.temIdValido()) {
            doadorViewModel.atualizar(doador);
        } else {
            doadorViewModel.inserir(doador);
        }
        acessarApp();
    }

    private void acessarApp() {
        Intent intencao = new Intent(this, MainActivity.class);
        startActivity(intencao);
    }

    private void obterDadosDoador() {
        campoCPF.setText(doador.getCpf());
        campoNome.setText(doador.getNome());
        campoEmail.setText(doador.getEmail());
        campoCeular.setText(doador.getCelular());
        campoSenha.setText(doador.getSenha());
    }

    public void cancelarDoador(View v) {
        finish();
    }


}