package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.activitys.animations.ProgressButton;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtEmail, edtSenha;
    private View myProgressButton;
    private ProgressButton progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialWork();

        myProgressButton.setOnClickListener(view -> {

            if (!edtEmail.getText().toString().isEmpty() && !edtSenha.getText().toString().isEmpty()) {
                progressButton = new ProgressButton(this, view);
                progressButton.buttonActivated();
                if (validadeEmailAddress(edtEmail.getText().toString())) {
                    logar();
                } else {
                    edtEmail.setError("E-mail inválido");
                }
            }
        });

    }

    private void logar() {
        ConfiguracaoFirebase.getFirebaseInstance().signInWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        progressButton.buttonFinishedSucess();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                    } else {
                        Toast.makeText(LoginActivity.this, "Erro ao logar", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initialWork() {
        edtEmail = findViewById(R.id.txt_email);
        edtSenha = findViewById(R.id.edt_senha);
        myProgressButton = findViewById(R.id.btn_acessar);
    }

    public boolean validadeEmailAddress(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;
        else
            return false;

    }
}