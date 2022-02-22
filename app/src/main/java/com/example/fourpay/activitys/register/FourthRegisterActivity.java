package com.example.fourpay.activitys.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.model.Conta;
import com.example.fourpay.model.Cliente;
import com.google.android.material.textfield.TextInputEditText;

public class FourthRegisterActivity extends AppCompatActivity {

    private Button btnContinuar;
    private TextInputEditText edtSenha;
    private TextInputEditText edtConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_register);

        Intent getIntent = getIntent();
        Cliente cliente = getIntent.getParcelableExtra(FifthRegisterActivity.KEY_CLIENTE);
        initialWork();


        btnContinuar.setOnClickListener(view -> {
            if (!edtSenha.getText().toString().isEmpty() && !edtConfirmarSenha.getText().toString().isEmpty()) {

                if(edtSenha.getText().toString().equals(edtConfirmarSenha.getText().toString())) {

                    Conta conta = new Conta();
                    conta.setSenhaAplicativo(Integer.parseInt(edtSenha.getText().toString()));
                    cliente.setConta(conta);

                    Intent intent = new Intent(FourthRegisterActivity.this, FifthRegisterActivity.class);
                    intent.putExtra(FifthRegisterActivity.KEY_CLIENTE, cliente);
                    startActivity(intent);
                } else {
                    edtConfirmarSenha.setError("As senhas precisam ser identicas");
                }


            } else {
                if (edtSenha.getText().toString().isEmpty()) {
                    edtSenha.setError("Preencha a senha");
                }
                if (edtConfirmarSenha.getText().toString().isEmpty()) {
                    edtConfirmarSenha.setError("Preencha a senha");
                }
            }

        });
    }

    public void voltar(View view) {
        finish();
    }

    public void initialWork() {
        btnContinuar = findViewById(R.id.btn_continuar);
        edtSenha = findViewById(R.id.til_senha);
        edtConfirmarSenha = findViewById(R.id.edt_confirmar_senha);

    }
}