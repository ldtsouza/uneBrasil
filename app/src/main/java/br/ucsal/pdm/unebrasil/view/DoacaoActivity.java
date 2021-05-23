package br.ucsal.pdm.unebrasil.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.DoacaoBuilder;

import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOACAO;

public class DoacaoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVA_DOACAO = "Nova Doação";
    private static final String TITULO_APPBAR_EDITA_DOACAO = "Editar Doação";

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    private TextInputEditText campoDoador;
    private TextInputEditText campoData;
    private TextInputEditText campoTipo;
    private TextInputEditText campoQtd;
    private TextInputEditText campoBeneficiario;

    private Doacao doacao;
    private DoacaoViewModel doacaoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_doacao);
        inicializacaoDosCampos();
        carregaDoacao();
        inicializacaoDoAdapter();
    }

    public void inicializacaoDosCampos() {
        campoDoador = findViewById(R.id.formDoacao_input_doador);
        campoData = findViewById(R.id.formDoacao_input_data);
        campoTipo = findViewById(R.id.formDoacao_input_tipo);
        campoQtd = findViewById(R.id.formDoacao_input_qtd);
        campoBeneficiario = findViewById(R.id.formDoacao_input_benef);
    }

    public void carregaDoacao() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_DOACAO)) {
            setTitle(TITULO_APPBAR_EDITA_DOACAO);
            doacao = (Doacao) dados.getSerializableExtra(CHAVE_DOACAO);
            obterDadosDoacao();
        } else {
            setTitle(TITULO_APPBAR_NOVA_DOACAO);
            doacao = new Doacao();
        }
    }

    private void preencherDadosDoacao() {

        DoacaoBuilder doacaoBuilder = DoacaoBuilder.novaDoacao();

        doacao = doacaoBuilder.mas()
                .doDoador(campoDoador.getText().toString())
                .noDia(campoData.getText().toString())
                .doTipo(campoTipo.getText().toString())
                .comQtd(campoQtd.getText().toString())
                .paraBeneficiario(campoBeneficiario.getText().toString())
                .build();
    }

    public void inicializacaoDoAdapter() {
        CustomAdapter customAdapter = new CustomAdapter(new CustomAdapter.DoacaoDiff());

        doacaoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DoacaoViewModel.class);

        doacaoViewModel.getDoacoes().observe(this, doacoes -> {
            customAdapter.submitList(doacoes);
        });
    }

    private void obterDadosDoacao() {
        campoDoador.setText(doacao.getDoador());
        campoData.setText(doacao.getDataDoacao());
        campoTipo.setText(doacao.getTipoDoacao());
        campoQtd.setText(doacao.getQtdDoacao());
        campoBeneficiario.setText(doacao.getBeneficiario());
    }

    public void finalizaFormulario(View v) {
        preencherDadosDoacao();
        if(doacao.temIdValido()) {
            doacaoViewModel.atualizar(doacao);
        } else {
            doacaoViewModel.inserir(doacao);
        }
        finish();
    }

    public void cancelarDoacao(View v) {
        finish();
    }

}