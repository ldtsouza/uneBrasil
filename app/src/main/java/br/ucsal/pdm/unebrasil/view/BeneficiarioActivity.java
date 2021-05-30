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
import br.ucsal.pdm.unebrasil.model.Beneficiario;
import br.ucsal.pdm.unebrasil.model.BeneficiarioBuilder;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_BENEFICIARIO;

public class BeneficiarioActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_BENEFICIARIO = "Novo Beneficiario";
    private static final String TITULO_APPBAR_EDITA_BENEFICIARIO = "Editar Beneficiario";

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

    private Beneficiario beneficiario;
    private BeneficiarioViewModel beneficiarioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_beneficiario);
        inicializacaoDosCampos();
        carregaBeneficiario();
        inicializacaoDoAdapter();
    }

    public void inicializacaoDosCampos() {
        tilCPF = findViewById(R.id.til_cadBene_cpfId);
        tilNome = findViewById(R.id.til_cadBene_nomeId);
        tilEmail = findViewById(R.id.til_cadBene_emailId);
        tilTel = findViewById(R.id.til_cadBene_telefoneId);
        tilSenha = findViewById(R.id.til_cadBene_senhaId);

        edtCPF = findViewById(R.id.edtinput_cadBene_cpfId);
        edtNome = findViewById(R.id.edtinput_cadBene_nomeId);
        edtEmail = findViewById(R.id.edtinput_cadBene_emailId);
        edtTel = findViewById(R.id.edtInput_cadBene_telefoneId);
        edtSenha = findViewById(R.id.edtInput_cadBene_senhaId);

        btnSalvar = findViewById(R.id.btn_cadBene_salvarId);
        btnCancelar = findViewById(R.id.btn_cadBenef_cancelarId);

        edtCPF.addTextChangedListener(MaskEditUtil.mask(edtCPF,MaskEditUtil.FORMAT_CPF));
        edtTel.addTextChangedListener(MaskEditUtil.mask(edtTel,MaskEditUtil.FORMAT_FONE));
    }

    public boolean verificarCampos() {
        tudoOk = true;

        if (edtCPF.getText().toString().trim().equals("")) {
            tilCPF.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilCPF.setError(null);
        }
        if (edtNome.getText().toString().trim().equals("")) {
            tilNome.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilNome.setError(null);
        }
        if (edtEmail.getText().toString().trim().equals("")) {
            tilEmail.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilEmail.setError(null);
        }
        if (edtTel.getText().toString().trim().equals("")) {
            tilTel.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilTel.setError(null);
        }
        if (edtSenha.getText().toString().trim().equals("")) {
            tilSenha.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilSenha.setError(null);
        }
        return tudoOk;
    }

    public void carregaBeneficiario() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_BENEFICIARIO)) {
            setTitle(TITULO_APPBAR_EDITA_BENEFICIARIO);
            beneficiario = (Beneficiario) dados.getSerializableExtra(CHAVE_BENEFICIARIO);
            obterDadosBeneficiario();
        } else {
            setTitle(TITULO_APPBAR_NOVO_BENEFICIARIO);
            beneficiario = new Beneficiario();
        }
    }

    private void preencherDadosBeneficiario() {
        BeneficiarioBuilder beneficiarioBuilder = BeneficiarioBuilder.novoBeneficiario();

        beneficiario = beneficiarioBuilder.mas()
                .comCPF(edtCPF.getText().toString())
                .comNome(edtNome.getText().toString())
                .comEmail(edtEmail.getText().toString())
                .comCelular(edtTel.getText().toString())
                .comSenha(edtSenha.getText().toString())
                .build();
    }

    public void inicializacaoDoAdapter() {
        BeneficiarioCustomAdapter beneficiarioCustomAdapter = new BeneficiarioCustomAdapter(new BeneficiarioCustomAdapter.BeneficiarioDiff());

        beneficiarioViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(BeneficiarioViewModel.class);

        beneficiarioViewModel.getBeneficiariosAll().observe(this, doadores -> {
            beneficiarioCustomAdapter.submitList(doadores);
        });
    }

    public void finalizaFormularioBeneficiario(View v) {
        verificarCampos();

        if (tudoOk) {
            preencherDadosBeneficiario();
            if(beneficiario.temIdValido()) {
                beneficiarioViewModel.atualizar(beneficiario);
            } else {
                beneficiarioViewModel.inserir(beneficiario);
            }
            acessarLogin();
        }
    }

    private void acessarLogin() {
        Intent intencao = new Intent(this, LoginActivity.class);
        startActivity(intencao);
    }

    private void obterDadosBeneficiario() {
        edtCPF.setText(beneficiario.getCpf());
        edtNome.setText(beneficiario.getNome());
        edtEmail.setText(beneficiario.getEmail());
        edtTel.setText(beneficiario.getCelular());
        edtSenha.setText(beneficiario.getSenha());
    }

    public void cancelarBeneficiario(View v) {
        finish();
    }
}