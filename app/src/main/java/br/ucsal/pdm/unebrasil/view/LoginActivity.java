package br.ucsal.pdm.unebrasil.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.repository.BancoDados;
import br.ucsal.pdm.unebrasil.repository.BancoDados_Impl;
import br.ucsal.pdm.unebrasil.repository.dao.BeneficiarioDAO;
import br.ucsal.pdm.unebrasil.repository.dao.DoacaoDAO;
import br.ucsal.pdm.unebrasil.repository.dao.DoadorDAO;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtInputCpf;
    private TextInputEditText edtInputsenha;
    private TextInputLayout tilCPF;
    private TextInputLayout tilSenha;

    private boolean tudoOk;
    private static String ERRO_CAMPO = "Campo obrigatório";

    BancoDados db;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        db = new BancoDados_Impl();
        inicializacaoDosCampos();
    }

    public void inicializacaoDosCampos() {
        edtInputCpf = findViewById(R.id.edtinput_login_cpfId);
        edtInputsenha = findViewById(R.id.edtinput_login_senhaId);
        tilCPF = findViewById(R.id.til_login_cpfId);
        tilSenha = findViewById(R.id.til_login_senhaId);

        edtInputCpf.addTextChangedListener(MaskEditUtil.mask(edtInputCpf, MaskEditUtil.FORMAT_CPF));
    }

    public void esqueciMinhaSenha(View view){
        Intent intencao = new Intent(this, EsqueciSenhaActivity.class);
        startActivity(intencao);
    }

    public void cadBeneficiario(View view){
        Intent intencao = new Intent(this, BeneficiarioActivity.class);
        startActivity(intencao);
    }

    public void cadDoador(View view){
        Intent intencao = new Intent(this, DoadorActivity.class);
        startActivity(intencao);
    }

    public void validarLogin(View view){
        verificarCampos();

        if (tudoOk) {
            String cpf = edtInputCpf.getText().toString();
            String senha = edtInputsenha.getText().toString();

            //db.getDoadorDao().obterTodos();

            if (cpf.equals("999.999.999-99") && senha.equals("123456")) {
                alert("Login realizado com sucesso!");
                Intent intencao = new Intent(this, MainActivity.class);
                startActivity(intencao);
            } else {
                alert("CPF ou senha inválido!");
            }
        }
    }

    private void alert (String alerta) {
        Toast.makeText(getApplicationContext(), alerta, Toast.LENGTH_LONG).show();
    }

    public boolean verificarCampos() {
        tudoOk = true;
        if (edtInputCpf.getText().toString().trim().equals("")) {
            tilCPF.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilCPF.setError(null);
        }

        if (edtInputsenha.getText().toString().trim().equals("")) {
            tilSenha.setError(ERRO_CAMPO);
            tudoOk = false;
        } else {
            tilSenha.setError(null);
        }
        return tudoOk;
    }
}