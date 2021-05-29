package br.ucsal.pdm.unebrasil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

public class BeneficiarioActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_DOADOR = "Novo Beneficiario";
    private static final String TITULO_APPBAR_EDITA_DOADOR = "Editar Beneficiario";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_beneficiario);

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

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarCampos();

                if (tudoOk) {
                    //Local de implementação de salvamento e redirecionamento de tela
                }
            }
        });
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


}