package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fourpay.R;

public class DigiteCodigoBarrasActivity extends AppCompatActivity {

    private Button btnContinuar;
    private EditText codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digite_codigo_barras);
        initializerWork();

        btnContinuar.setOnClickListener(view -> {

            if (!codigo.getText().toString().isEmpty()) {
                startActivity(new Intent(this, ConfirmarPagamentoActivity.class));
            }else{
                codigo.setError("Adicione o codigo de barras");
            }
        });
    }

    public void initializerWork(){
        btnContinuar = findViewById(R.id.btnContinuar3);
        codigo = findViewById(R.id.edt_complemento);

    }

    public void voltarParaHome(View view) {
        startActivity(new Intent(DigiteCodigoBarrasActivity.this, HomeActivity.class));
        finish();
    }
}