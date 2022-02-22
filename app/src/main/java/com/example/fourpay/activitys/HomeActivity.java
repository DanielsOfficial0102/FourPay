package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import com.example.fourpay.R;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class HomeActivity extends AppCompatActivity {

    private AppCompatAutoCompleteTextView txtSaldo;
    private TextView txtNome;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtSaldo = findViewById(R.id.tvSaldo);
        txtNome = findViewById(R.id.tvNome);
        ConfiguracaoFirebase.getNome().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nome = snapshot.getValue(String.class);
                String  nomeUser [] = nome.split(" ");
                txtNome.setText("Olá, " + nomeUser[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Erro ao buscar o nome", Toast.LENGTH_SHORT).show();
            }
        });

        ConfiguracaoFirebase.getSaldo().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.getValue().toString();
                DecimalFormat mask = new DecimalFormat("#,##0.00");
                String temp2 = mask.format(Float.parseFloat(temp));
                txtSaldo.setText(temp2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeActivity.this, "Erro ao carregar saldo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void abrirMeusCartoes(View view) {
        startActivity(new Intent(HomeActivity.this, MeusCartoesActivity.class));
    }

    public void abrirEmprestimo(View view) {
        startActivity(new Intent(HomeActivity.this, EmprestimoActivity.class));
    }

    public void abrirTelaPix(View view) {
        startActivity(new Intent(HomeActivity.this, PixActivity.class));
    }

    public void abrirScanQrCode(View view) {
        startActivity(new Intent(HomeActivity.this, ScanQRCodeActivity.class));
    }

    public void abrirTransferencia(View view) {
        startActivity(new Intent(HomeActivity.this, TransferenciaActivity.class));
    }

    public void abrirRecarga(View view) {
        startActivity(new Intent(HomeActivity.this, RecargaActivity.class));
    }

    public void abrirConfiguracao(View view) {
        startActivity(new Intent(HomeActivity.this, ConfiguracaoActivity.class));
    }

    public void abrirDeposito(View view) {
        startActivity(new Intent(HomeActivity.this, DepositoActivity.class));
    }
}