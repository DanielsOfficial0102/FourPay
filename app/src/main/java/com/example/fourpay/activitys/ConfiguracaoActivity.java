package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.fourpay.R;
import com.example.fourpay.model.Cliente;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class ConfiguracaoActivity extends AppCompatActivity {

    private TextView txtNome, txtRenda, txtEmail, txtNumero, txtEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        initialWork();

        ConfiguracaoFirebase.getCurrentClient().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cliente cliente = snapshot.getValue(Cliente.class);
                txtNome.setText(cliente.getNome());
                txtEmail.setText(cliente.getEmail());
                txtNumero.setText(cliente.getCelular());
                DecimalFormat mask = new DecimalFormat("#,##0.00");
                String saldoMostra = mask.format(cliente.getRenda());
                txtRenda.setText("R$ " + saldoMostra);
                txtEndereco.setText(cliente.getEndereço().getCidade() + " - " + cliente.getEndereço().getEstado());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void initialWork() {
        txtEmail = findViewById(R.id.txt_email_configuracao);
        txtNome = findViewById(R.id.txt_nome_configuracao);
        txtNumero = findViewById(R.id.txt_numero_configuracao);
        txtRenda = findViewById(R.id.txt_renda_configuracao);
        txtEndereco = findViewById(R.id.txt_endereco_configuracao);
    }

    public void voltar(View view) {
        finish();
    }
}