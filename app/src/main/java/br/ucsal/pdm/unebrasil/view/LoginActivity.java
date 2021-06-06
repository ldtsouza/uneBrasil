package br.ucsal.pdm.unebrasil.view;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Doador;
import br.ucsal.pdm.unebrasil.repository.BancoDados;
import br.ucsal.pdm.unebrasil.repository.dao.DoadorDAO;
import br.ucsal.pdm.unebrasil.utils.MaskEditUtil;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtInputCpf;
    private TextInputEditText edtInputsenha;
    private TextInputLayout tilCPF;
    private TextInputLayout tilSenha;
    private Button btnentrar;

    private boolean tudoOk;
    private static String ERRO_CAMPO = "Campo obrigatório";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        inicializacaoDosCampos();

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCampos();
                BancoDados bancoDados = BancoDados.getInstance(getApplicationContext());
                final DoadorDAO doadorDAO = bancoDados.getDoadorDao();
                if (tudoOk) {
                    final String cpf = edtInputCpf.getText().toString();
                    final String senha = edtInputsenha.getText().toString();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Doador doador = doadorDAO.autenticar(cpf, senha);
                            if (doador == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "CPF ou Senha inválidos", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                String cpf = doador.getCpf();
                                String nome = doador.getNome();
                                String data = doador.getData();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                        .putExtra("cpf", cpf)
                                        .putExtra("nome", nome)
                                        .putExtra("data", data));
                            }
                        }
                    }).start();
                }
            }
        });
    }

    public void inicializacaoDosCampos() {
        edtInputCpf = findViewById(R.id.edtinput_login_cpfId);
        edtInputsenha = findViewById(R.id.edtinput_login_senhaId);
        tilCPF = findViewById(R.id.til_login_cpfId);
        tilSenha = findViewById(R.id.til_login_senhaId);

        btnentrar = findViewById(R.id.button_login_entrar);

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