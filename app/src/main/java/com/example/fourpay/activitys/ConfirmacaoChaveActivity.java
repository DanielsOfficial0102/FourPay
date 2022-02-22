package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.SenhaFragment;

public class ConfirmacaoChaveActivity extends AppCompatActivity {
    static final String KEY = "KEY";
    static final String KEY_VALOR = "VALOR";
    private TextView txtChave, txtValor;
    private CheckBox checkConcordo;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_chave_confirmacao);
        initialWork();

        Intent getIntent = getIntent();
        String chave = getIntent.getStringExtra(KEY);
        Float valor = getIntent.getFloatExtra(KEY_VALOR, 0f);

        txtChave.setText(chave);
        txtValor.setText(String.valueOf(valor));

        btnContinuar.setOnClickListener(view -> {
            if(checkConcordo.isChecked()){
                replaceFragment(new SenhaFragment());
                btnContinuar.setVisibility(View.INVISIBLE);
            }

        });
    }

    private void initialWork() {
        txtChave = findViewById(R.id.txt_chave_pix);
        txtValor = findViewById(R.id.txt_valor_pix);
        checkConcordo = findViewById(R.id.check_concordo);
        btnContinuar = findViewById(R.id.btn_continuar_pix);
    }

    public void abrirTermos(View view) {
        startActivity(new Intent(ConfirmacaoChaveActivity.this, TermosActivity.class));
    }

    private void replaceFragment(SenhaFragment senhaFragment) {
        btnContinuar.setVisibility(View.INVISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, senhaFragment);
        fragmentTransaction.commit();
    }
}