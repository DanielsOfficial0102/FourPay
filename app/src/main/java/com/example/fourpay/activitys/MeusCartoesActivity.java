package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MeusCartoesActivity extends AppCompatActivity {

    private TextView txtCartao1, txtCartao2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_cartoes);

        initialWork();

        ConfiguracaoFirebase.getNome().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String tempNome = snapshot.getValue(String.class);
                txtCartao1.setText(tempNome);
                txtCartao2.setText(tempNome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MeusCartoesActivity.this, "Erro ao buscar nome", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initialWork() {
        txtCartao1 = findViewById(R.id.nome_cartao_1);
        txtCartao2 = findViewById(R.id.nome_cartao_2);
    }

    public void voltar(View view) {
        finish();
    }
}