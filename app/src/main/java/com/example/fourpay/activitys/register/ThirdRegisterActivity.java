package com.example.fourpay.activitys.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fourpay.R;
import com.example.fourpay.model.Cliente;

public class ThirdRegisterActivity extends AppCompatActivity {

    private Button btnContinuar;
    private EditText edtRenda;
    private EditText edtEstadoCivil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_register);

        Intent getIntent = getIntent();
        Cliente cliete = getIntent.getParcelableExtra(FifthRegisterActivity.KEY_CLIENTE);
        initialWork();

        btnContinuar.setOnClickListener(view -> {
            if (!edtRenda.getText().toString().isEmpty() &&
                    !edtEstadoCivil.getText().toString().isEmpty()) {

                cliete.setRenda(Float.parseFloat(edtRenda.getText().toString()));
                cliete.setEstadoCivil(edtEstadoCivil.getText().toString());

                Intent intent = new Intent(ThirdRegisterActivity.this, FourthRegisterActivity.class);
                intent.putExtra(FifthRegisterActivity.KEY_CLIENTE, cliete);
                startActivity(intent);

            } else {

                if (edtRenda.getText().toString().isEmpty()) {
                    edtRenda.setError("Renda obrigatória");
                }
                if (edtEstadoCivil.getText().toString().isEmpty()) {
                    edtEstadoCivil.setError("Estado civil obrigatório");
                }
            }

        });
    }

    public void voltar(View view) {
        finish();
    }

    public void initialWork() {
        btnContinuar = findViewById(R.id.btn_continuar);
        edtRenda = findViewById(R.id.edt_renda);
        edtEstadoCivil = findViewById(R.id.edt_estado_civil);

    }
}