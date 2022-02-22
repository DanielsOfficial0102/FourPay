package com.example.fourpay.activitys.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.activitys.FotoActivity;
import com.example.fourpay.activitys.HomeActivity;
import com.example.fourpay.activitys.PixDadosActivity;
import com.example.fourpay.model.Cliente;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;

public class FifthRegisterActivity extends AppCompatActivity {

    static final String KEY_CLIENTE = "CLIENTE";

    private Button btnContinuar;
    private TextInputEditText edtSenha;
    private TextInputEditText edtConfirmarSenha;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_register);

        Intent getIntent = getIntent();
        cliente = getIntent.getParcelableExtra(FifthRegisterActivity.KEY_CLIENTE);
        initialWork();

        btnContinuar.setOnClickListener(view -> {
            if (!edtSenha.getText().toString().isEmpty() && !edtConfirmarSenha.getText().toString().isEmpty()) {

                if (edtSenha.getText().toString().equals(edtConfirmarSenha.getText().toString())) {
                    cliente.getConta().setSenhaCartao(Integer.parseInt(edtSenha.getText().toString()));

                    criarConta();

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

    private void opcoesErro(String resposta) {
        if (resposta.contains("least 6 characters")) {
            Toast.makeText(this, "A senha deve conter 6 dígitos", Toast.LENGTH_SHORT).show();
            edtSenha.setError("A senha deve conter 6 dígitos");
        } else if (resposta.contains("address is badly")) {
            Toast.makeText(this, "O email é inválido", Toast.LENGTH_SHORT).show();
        } else if (resposta.contains("address is already")) {
            Toast.makeText(this, "O email digitado já existe", Toast.LENGTH_SHORT).show();
        } else if (resposta.contains("interrupted connection")) {
            Toast.makeText(this, "Sem conexão com a internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void criarConta() {
        ConfiguracaoFirebase.getFirebaseInstance().createUserWithEmailAndPassword(cliente.getEmail(), cliente.getConta().getSenhaAplicativo().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = ConfiguracaoFirebase.getUser();
                        if (user != null)
                            cliente.setId(user.getUid());
                        cliente.salvarDados();
                        Intent intent = new Intent(FifthRegisterActivity.this, FotoActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        String erro = task.getException().toString();
                        opcoesErro(erro);
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