package com.example.fourpay.activitys;

import static com.example.fourpay.network.ConfiguracaoFirebase.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.SenhaFragment;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class RecargaValidacaoActivity extends AppCompatActivity {

    static final String KEY_NUMERO = "NUMERO";
    static final String KEY_VALOR = "VALOR";
    static final String KEY_OPERADORA = "OPERADORA";
    static final String KEY_PAGAMENTO = "PAGAMENTO";
    private FrameLayout frame;
    private Button btnConfirmar;
    private DatabaseReference buscaSaldo;
    public static Float saldo;
    private TextView txtNumero, txtValor, txtOperadora, txtPagamento;
    public static Float valRecarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga_validacao);

        initialWork();

        Intent intent = getIntent();
        String numero = intent.getStringExtra(KEY_NUMERO);
        String valor = intent.getStringExtra(KEY_VALOR);
        String operadora = intent.getStringExtra(KEY_OPERADORA);
        String pagamento = intent.getStringExtra(KEY_PAGAMENTO);

        txtValor.setText(valor);
        txtNumero.setText(numero);
        txtOperadora.setText(operadora);
        txtPagamento.setText(pagamento);

        buscaSaldo();
        valRecarga = Float.parseFloat(valor);

        btnConfirmar.setOnClickListener(view -> {

            if (valRecarga > saldo) {
                Toast.makeText(this, "Ops! Seu saldo é insuficiente para realizar essa recarga", Toast.LENGTH_LONG).show();
            } else {
                btnConfirmar.setVisibility(View.INVISIBLE);
                replaceFragment(new SenhaFragment());
            }

        });

    }
    private void buscaSaldo() {
        buscaSaldo = ConfiguracaoFirebase.getSaldo();
        buscaSaldo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String saldoUser = snapshot.getValue().toString();
                saldo = Float.parseFloat(saldoUser);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String mensagem = ("Erro ao buscar saldo");
            }
        });
    }

    private void replaceFragment(SenhaFragment senhaFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, senhaFragment);
        fragmentTransaction.commit();
    }

    private void initialWork() {
        txtNumero = findViewById(R.id.txt_numero);
        txtValor = findViewById(R.id.txt_valor);
        txtOperadora = findViewById(R.id.txt_operadora);
        txtPagamento = findViewById(R.id.txt_pagamento);
        btnConfirmar = findViewById(R.id.btnConfirmar);
        frame = findViewById(R.id.frame);
    }

    public void voltar(View view) {
        finish();
    }
}