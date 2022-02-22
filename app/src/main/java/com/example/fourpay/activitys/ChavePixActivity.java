package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.google.android.material.textfield.TextInputEditText;

public class ChavePixActivity extends AppCompatActivity {

    static final String KEY = "TIPO_CHAVE";
    static final String EMAIL = "EMAIL";
    static final String CELULAR = "CELULAR";
    static final String CPF_CNPJ = "CPF_CNPJ";
    static final String CHAVE_ALEATORIA = "CHAVE_ALEATORIA";

    private TextView txtChave, txtChaveDescricao;
    private TextInputEditText edtChave;
    private Button btnAvancar;
    private ImageView imgVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chave_pix);

        initialWork();

        Intent intent = getIntent();
        String chave = intent.getStringExtra(KEY);

        if (chave.equals(EMAIL)) {
            txtChave.setText(getString(R.string.pix_email));
            txtChaveDescricao.setText(getString(R.string.pix_email_descricao));
            edtChave.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            edtChave.setHint("Digite o e-mail");
        } else if (chave.equals(CELULAR)) {
            txtChave.setText(getString(R.string.pix_celular));
            txtChaveDescricao.setText(getString(R.string.pix_celular_descricao));
            edtChave.setInputType(InputType.TYPE_CLASS_PHONE);
            edtChave.setHint("Digite o numero do celular");
        } else if (chave.equals(CPF_CNPJ)) {
            txtChave.setText(getString(R.string.pix_cpf_cnpj));
            txtChaveDescricao.setText(getString(R.string.pix_cpf_cnpj_descricao));
            edtChave.setInputType(InputType.TYPE_CLASS_NUMBER);
            edtChave.setHint("Digite o CPF ou CNPJ");
        } else if (chave.equals(CHAVE_ALEATORIA)) {
            txtChave.setText(getString(R.string.pix_chave_aleatoria));
            txtChaveDescricao.setText(getString(R.string.pix_chave_aleatoria_descricao));
            edtChave.setInputType(InputType.TYPE_CLASS_TEXT);
            edtChave.setHint("Digite a chave aleatória");
        } else {
            Toast.makeText(this, "Erro ao buscar dados do pix", Toast.LENGTH_SHORT).show();
        }

        btnAvancar.setOnClickListener(view -> {
            Intent intent1 = new Intent(ChavePixActivity.this, PixDadosActivity.class);
            intent1.putExtra(PixDadosActivity.KEY, edtChave.getText().toString());
            startActivity(intent1);
        });

        imgVoltar.setOnClickListener(view -> {
            finish();
        });
    }

    private void initialWork() {
        txtChave = findViewById(R.id.txt_chave);
        txtChaveDescricao = findViewById(R.id.txt_chave_descricao);
        edtChave = findViewById(R.id.edt_complemento);
        btnAvancar = findViewById(R.id.btn_avancar);
        imgVoltar = findViewById(R.id.img_seta_esquerda);
    }

    public void voltar(View view) {
        finish();
    }
}