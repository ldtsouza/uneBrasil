package br.ucsal.pdm.unebrasil.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.model.DoadorBuilder;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOACAO;
import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOADOR;

public class DoadorActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_DOADOR = "Novo Doador";
    private static final String TITULO_APPBAR_EDITA_DOADOR = "Editar Doador";

    private TextInputLayout tilCPF;
    private TextInputLayout tilNome;
    private TextInputLayout tilEmail;
    private TextInputLayout tilTel;
    private TextInputLayout tilSenha;
    private TextInputEditText edtCPF;
    private TextInputEditText edtNome;
    private TextInputEditText edtEmail;
    private TextInputEditText edtTel;
    private TextInputEditText edtSenha;
    private Button btnSalvar;
    private Button btnCancelar;
    private boolean tudoOk;
    private static String ERRO_CAMPO = "Campo obrigatório";


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
        tilCPF = findViewById(R.id.til_cadDoador_CPFId);
        tilNome = findViewById(R.id.til_cadDoador_nomeId);
        tilEmail = findViewById(R.id.til_cadDoador_emailId);
        tilTel = findViewById(R.id.til_cadDoador_telefoneId);
        tilSenha = findViewById(R.id.til_cadDoador_senhaId);
        edtCPF = findViewById(R.id.edtInput_cadDoador_CPFId);
        edtNome = findViewById(R.id.edtInput_cadDoador_nomeId);
        edtEmail = findViewById(R.id.edtInput_cadDoador_emailId);
        edtTel = findViewById(R.id.edtInput_cadDoador_telefoneId);
        edtSenha = findViewById(R.id.edtInput_cadDoador_senhaId);

        btnSalvar = findViewById(R.id.btn_cadDoador_salvarId);
        btnCancelar = findViewById(R.id.btn_cadDoador_cancelarId);

        edtCPF.addTextChangedListener(MaskEditUtil.mask(edtCPF,MaskEditUtil.FORMAT_CPF));
        edtTel.addTextChangedListener(MaskEditUtil.mask(edtTel,MaskEditUtil.FORMAT_FONE));
    }
    public boolean verificarCampos() {
        tudoOk = true;

        if (edtCPF.getText().toString().trim().equals("")) {
            tilCPF.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilCPF.setError(null);
        }

        if (edtNome.getText().toString().trim().equals("")) {
            tilNome.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilNome.setError(null);
        }

        if (edtEmail.getText().toString().trim().equals("")) {
            tilEmail.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilEmail.setError(null);
        }

        if (edtTel.getText().toString().trim().equals("")) {
            tilTel.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilTel.setError(null);
        }

        if (edtSenha.getText().toString().trim().equals("")) {
            tilSenha.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilSenha.setError(null);
        }
        return tudoOk;
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
                .comCPF(edtCPF.getText().toString())
                .comNome(edtNome.getText().toString())
                .comEmail(edtEmail.getText().toString())
                .comCelular(edtTel.getText().toString())
                .comSenha(edtSenha.getText().toString())
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
        verificarCampos();

        if (tudoOk) {
            preencherDadosDoador();
            if(doador.temIdValido()) {
                doadorViewModel.atualizar(doador);
            } else {
                doadorViewModel.inserir(doador);
            }
            acessarLogin();
        }
    }

    private void acessarLogin() {
        Intent intencao = new Intent(this, LoginActivity.class);
        startActivity(intencao);
    }

    private void obterDadosDoador() {
        edtCPF.setText(doador.getCpf());
        edtNome.setText(doador.getNome());
        edtEmail.setText(doador.getEmail());
        edtTel.setText(doador.getCelular());
        edtSenha.setText(doador.getSenha());
    }

    public void cancelarDoador(View v) {
        finish();
    }


}