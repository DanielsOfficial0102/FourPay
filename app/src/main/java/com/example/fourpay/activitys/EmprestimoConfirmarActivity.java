package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.SenhaFragment;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class EmprestimoConfirmarActivity extends AppCompatActivity {

    static final String KEY_VALOR_SIMULADO = "VALOR";
    static final String KEY_PARCELAS = "PERCELAS";
    static final String KEY_DIA_PAGAMENTO = "DIA_PAGAMENTO";

    private TextView txtValorSimulado, txtParcelas, txtDia;
    private Button btnContratar;

    public static Float saldoAtual;
    public static Float valorContratar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimo_confirmar);

        initialWork();
        Intent intent = getIntent();
        String diaPagamento = intent.getStringExtra(KEY_DIA_PAGAMENTO);
        valorContratar = intent.getFloatExtra(KEY_VALOR_SIMULADO, 0f);
        String qtdParcelas = intent.getStringExtra(KEY_PARCELAS);

        txtValorSimulado.setText(String.valueOf(valorContratar));
        txtDia.setText(diaPagamento);
        txtParcelas.setText(qtdParcelas);

        ConfiguracaoFirebase.getSaldo().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.getValue().toString();
                saldoAtual = Float.parseFloat(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EmprestimoConfirmarActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnContratar.setOnClickListener(view -> {
            replaceFragment(new SenhaFragment());
        });

    }

    private void initialWork() {
        txtValorSimulado = findViewById(R.id.txt_valor_emprestimo);
        txtDia = findViewById(R.id.txt_data_pagamento);
        txtParcelas = findViewById(R.id.txt_quantidade_parcelas);
        btnContratar = findViewById(R.id.btn_contratar);
    }

    public void voltar(View view) {
        finish();
    }

    private void replaceFragment(SenhaFragment senhaFragment) {
        btnContratar.setVisibility(View.INVISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, senhaFragment);
        fragmentTransaction.commit();
    }
}