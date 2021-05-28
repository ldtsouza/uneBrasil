package br.ucsal.pdm.unebrasil.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.ucsal.pdm.unebrasil.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
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
        //Implementar regra de validação de login
        Intent intencao = new Intent(this, MainActivity.class);
        startActivity(intencao);
    }
}