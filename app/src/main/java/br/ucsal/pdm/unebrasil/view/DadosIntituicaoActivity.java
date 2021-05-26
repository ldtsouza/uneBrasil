package br.ucsal.pdm.unebrasil.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.ucsal.pdm.unebrasil.R;
import br.ucsal.pdm.unebrasil.model.Instituicao;

public class DadosIntituicaoActivity extends AppCompatActivity {

    private Instituicao instituicao;
    private TextView textViewRazao;
    private TextView textViewCNPJ;
    private TextView textViewTelefone;
    private TextView textViewEmail;
    private TextView textViewSite;
    private TextView textViewHistorico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_instituicao);
        iniciarInstituicao();
        configuraDadosInstituicao();
        adicionaDadosInstituicao();
    }

    public void iniciarInstituicao() {
        instituicao = new Instituicao();
    }

    public void configuraDadosInstituicao() {
        textViewRazao = findViewById(R.id.dados_instituicao_razao);
        textViewCNPJ = findViewById(R.id.dados_instituicao_cnpj);
        textViewTelefone = findViewById(R.id.dados_instituicao_telefone);
        textViewEmail = findViewById(R.id.dados_instituicao_email);
        textViewSite = findViewById(R.id.dados_instituicao_site);
        textViewHistorico = findViewById(R.id.dados_instituicao_historico);
    }

    public void adicionaDadosInstituicao() {
        textViewRazao.setText(instituicao.getRazao());
        textViewCNPJ.setText(instituicao.getCnpj());
        textViewTelefone.setText(instituicao.getTelefone());
        textViewEmail.setText(instituicao.getEmail());
        textViewSite.setText(instituicao.getSite());
        textViewHistorico.setText(instituicao.getHistorico());
    }
}
