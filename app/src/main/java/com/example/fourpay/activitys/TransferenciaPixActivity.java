package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fourpay.R;

public class TransferenciaPixActivity extends AppCompatActivity {

    private Button btnEmail, btnCelular, btnCpfCnpj, btnChaveAleatoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia_pix);

        initialWork();

        btnEmail.setOnClickListener(view -> {
            Intent intent = new Intent(TransferenciaPixActivity.this, ChavePixActivity.class);
            intent.putExtra(ChavePixActivity.KEY, ChavePixActivity.EMAIL);
            startActivity(intent);
        });

        btnCelular.setOnClickListener(view -> {
            Intent intent = new Intent(TransferenciaPixActivity.this, ChavePixActivity.class);
            intent.putExtra(ChavePixActivity.KEY, ChavePixActivity.CELULAR);
            startActivity(intent);
        });

        btnCpfCnpj.setOnClickListener(view -> {
            Intent intent = new Intent(TransferenciaPixActivity.this, ChavePixActivity.class);
            intent.putExtra(ChavePixActivity.KEY, ChavePixActivity.CPF_CNPJ);
            startActivity(intent);
        });

        btnChaveAleatoria.setOnClickListener(view -> {
            Intent intent = new Intent(TransferenciaPixActivity.this, ChavePixActivity.class);
            intent.putExtra(ChavePixActivity.KEY, ChavePixActivity.CHAVE_ALEATORIA);
            startActivity(intent);
        });
    }

    private void initialWork() {
        btnEmail = findViewById(R.id.img_email);
        btnCelular = findViewById(R.id.img_celular);
        btnCpfCnpj = findViewById(R.id.img_cpf_cnpj);
        btnChaveAleatoria = findViewById(R.id.img_cheave_aleatoria);
    }

    public void voltarTelaHome(View view) {
        startActivity(new Intent(TransferenciaPixActivity.this, HomeActivity.class));
    }
}