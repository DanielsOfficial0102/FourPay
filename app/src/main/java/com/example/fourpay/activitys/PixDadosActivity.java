package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.SenhaFragment;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class PixDadosActivity extends AppCompatActivity {
    static final String KEY = "KEY";

    private AppCompatAutoCompleteTextView tvSaldo;
    private EditText valorPagar;
    private Button btnContinuar;
    private FrameLayout frame;
    public static Float saldoAtual;
    public static Float valorPix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix_dados);
        initializerWork();

        ConfiguracaoFirebase.getSaldo().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                saldoAtual = snapshot.getValue(Float.class);
                DecimalFormat mask = new DecimalFormat("#,##0.00");
                String saldoMostra = mask.format(saldoAtual);
                tvSaldo.setText(saldoMostra);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PixDadosActivity.this, "Erro ao buscar saldo", Toast.LENGTH_SHORT).show();
            }
        });

        btnContinuar.setOnClickListener(view -> {
            if (!valorPagar.getText().toString().isEmpty()) {
                valorPix = Float.parseFloat(valorPagar.getText().toString());

                if(valorPix <= saldoAtual) {
                    Intent getIntent = getIntent();
                    String chave = getIntent.getStringExtra(KEY);

                    Intent intent = new Intent(PixDadosActivity.this, ConfirmacaoChaveActivity.class);
                    intent.putExtra(ConfirmacaoChaveActivity.KEY, chave);
                    intent.putExtra(ConfirmacaoChaveActivity.KEY_VALOR, valorPix);
                    startActivity(intent);
                } else {
                    valorPagar.setError("Saldo insuficiente");
                }

            } else {
                valorPagar.setError("Preencha o campo");
            }

        });

    }

    public void initializerWork() {
        valorPagar = findViewById(R.id.editValorPagar);
        btnContinuar = findViewById(R.id.btnContinuar2);
        frame = findViewById(R.id.frame);
        tvSaldo = findViewById(R.id.tvSaldo);
    }

    public void voltar(View view) {
        finish();
    }
}