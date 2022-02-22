package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DepositoActivity extends AppCompatActivity {

    private TextView edtValor;
    private Button btnBoleto;
    private Float saldoAtual;
    private Float saldoDeposito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito);

        edtValor = findViewById(R.id.edt_valor_deposito);
        btnBoleto = findViewById(R.id.btn_gerar_boleto);
        buscarSaldo();

        btnBoleto.setOnClickListener(view -> {
            if (!edtValor.getText().toString().isEmpty() && Float.parseFloat(edtValor.getText().toString()) >= 20f) {
                saldoDeposito = Float.parseFloat(edtValor.getText().toString());
                atualizaSaldo();
                Intent intent = new Intent(DepositoActivity.this, PagamentoConfirmadoActivity.class);
                intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_DEPOSITO);
                startActivity(intent);
                finish();
            } else {
                if (!edtValor.getText().toString().isEmpty())
                    edtValor.setError("Valor inválido");
                else
                    edtValor.setError("Campo obrigatório");
            }
        });
    }

    void atualizaSaldo() {
        ConfiguracaoFirebase.setSaldo(saldoAtual + saldoDeposito);
    }

    void buscarSaldo() {
        ConfiguracaoFirebase.getSaldo().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.getValue().toString();
                saldoAtual = Float.parseFloat(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DepositoActivity.this, "Erro ao adicionar saldo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}