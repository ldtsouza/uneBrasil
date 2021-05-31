package br.ucsal.pdm.unebrasil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doacao;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private TextInputEditText edtInputEmail;
    private TextInputLayout tilEmail;

    private boolean tudoOk;
    private static final String ERRO_CAMPO = "Campo obrigatório";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        edtInputEmail = findViewById(R.id.edt_esqueciSenha_emailId);
        tilEmail = findViewById(R.id.til_esqueciSenha_emailLayoutId);
    }

    public void enviarEmail(View view){
        verificarCampo();

        if (tudoOk) {
            //Disparar e-mail para troca de senha
        }
    }

    public boolean verificarCampo() {
        tudoOk = true;

        if (edtInputEmail.getText().toString().trim().equals("")) {
            tilEmail.setError(ERRO_CAMPO);
            tudoOk = false;
        }else {
            tilEmail.setError(null);
        }
        return tudoOk;
    }
}
