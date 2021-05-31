package br.ucsal.pdm.unebrasil.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;
import br.ucsal.pdm.unebrasil.model.DoacaoBuilder;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

import static br.ucsal.pdm.unebrasil.view.ConstantesActivities.CHAVE_DOACAO;

public class DoacaoActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private TextInputLayout til_doacao;
    private TextInputLayout til_dataEntrega;
    private TextInputEditText edtInput_dataEntrega;
    private TextInputLayout til_quant;
    private TextInputEditText edtInput_quant;
    private Button btn_agendar;
    private Button btn_cancelar;

    private static final String TITULO_APPBAR_NOVA_DOACAO = "Nova Doação";
    private static final String TITULO_APPBAR_EDITA_DOACAO = "Editar Doação";

    private boolean tudoOk;
    private static final String ERRO_CAMPO = "Campo obrigatório";

    private Doacao doacao;
    private DoacaoViewModel doacaoViewModel;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doacao);

        inicializacaoDosCampos();
        carregaDoacao();
        inicializacaoDoAdapter();

        edtInput_dataEntrega.addTextChangedListener(MaskEditUtil.mask(edtInput_dataEntrega, MaskEditUtil.FORMAT_DATE));

        String[] opcoes = {"Cesta Básica","Kit de Higiene"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.dropdown_item,opcoes);

        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);
        autoCompleteTextView.setAdapter(arrayAdapter);

    }

    public void inicializacaoDosCampos() {
        til_dataEntrega = findViewById(R.id.til_doacao_dataEntregaId);
        til_quant = findViewById(R.id.til_doacao_quantId);
        edtInput_dataEntrega = findViewById(R.id.edtinput_doacao_dataEntregaId);
        edtInput_quant = findViewById(R.id.edtInput_doacao_quantId);
        til_doacao = findViewById(R.id.til_docao_listaId);
        autoCompleteTextView = findViewById(R.id.autCompText_doacao_doacaoId);

        btn_agendar = findViewById(R.id.btn_doacao_agendar);
        btn_cancelar = findViewById(R.id.btn_doacao_cancelar);
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

    private void obterDadosDoacao() {
        edtInput_dataEntrega.setText(doacao.getDataDoacao());
        autoCompleteTextView.setText(doacao.getTipoDoacao());
        edtInput_quant.setText(doacao.getQtdDoacao());
    }

    public void inicializacaoDoAdapter() {
        MainCustomAdapter mainCustomAdapter = new MainCustomAdapter(new MainCustomAdapter.DoacaoDiff());

        doacaoViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DoacaoViewModel.class);

        doacaoViewModel.getDoacoes().observe(this, doacoes -> {
            mainCustomAdapter.submitList(doacoes);
        });
    }

    public boolean verificarCampo() {
        tudoOk = true;

        if (edtInput_dataEntrega.getText().toString().trim().equals("")) {
            til_dataEntrega.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            til_dataEntrega.setError(null);
        }

        if (edtInput_quant.getText().toString().trim().equals("")) {
            til_quant.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            til_quant.setError(null);
        }

        if (autoCompleteTextView.getText().toString().trim().equals("")) {
            til_doacao.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            til_doacao.setError(null);
        }
        return tudoOk;
    }

    public void agendar(View view){

        if (verificarCampo()){
            preencherDadosDoacao();
            if(doacao.temIdValido()) {
                doacaoViewModel.atualizar(doacao);
            } else {
                doacaoViewModel.inserir(doacao);
            }
            finish();
        }
    }

    private void preencherDadosDoacao() {
        Log.i("info","Data: "
                + edtInput_dataEntrega.getText().toString() +
                "doação" + autoCompleteTextView.getText().toString() +
                "quantidade" + edtInput_quant.getText().toString());
        DoacaoBuilder doacaoBuilder = DoacaoBuilder.novaDoacao();

        doacao = doacaoBuilder.mas()

                .noDia(edtInput_dataEntrega.getText().toString())
                .doTipo(autoCompleteTextView.getText().toString())
                .comQtd(edtInput_quant.getText().toString())

                .build();
    }

    public void cancelar(View view){
        finish();
    }
}