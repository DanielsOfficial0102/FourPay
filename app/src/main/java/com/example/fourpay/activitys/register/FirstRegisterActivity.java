package com.example.fourpay.activitys.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fourpay.R;
import com.example.fourpay.model.Cliente;
import com.google.android.material.textfield.TextInputEditText;

public class FirstRegisterActivity extends AppCompatActivity {

    private Button btnContinuar;
    private TextInputEditText edt_nome;
    private TextInputEditText edt_data_nascimento;
    private TextInputEditText edt_cpf;
    private TextInputEditText edt_rg;
    private TextInputEditText edt_email;
    private TextInputEditText edt_celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_register);

        initialWork();

        btnContinuar.setOnClickListener(view -> {

            if (!edt_nome.getText().toString().isEmpty() &&
                    !edt_data_nascimento.getText().toString().isEmpty() &&
                    !edt_cpf.getText().toString().isEmpty() &&
                    !edt_rg.getText().toString().isEmpty() &&
                    !edt_email.getText().toString().isEmpty() &&
                    !edt_celular.getText().toString().isEmpty()) {

                Cliente cliente = new Cliente();

                cliente.setNome(edt_nome.getText().toString());
                cliente.setCelular(edt_celular.getText().toString());
                cliente.setCpf(edt_cpf.getText().toString());
                cliente.setRg(edt_rg.getText().toString());
                cliente.setEmail(edt_email.getText().toString());
                cliente.setDataNascimento(edt_data_nascimento.getText().toString());

                Intent intent = new Intent(this, SecondRegisterActivity.class);
                intent.putExtra(FifthRegisterActivity.KEY_CLIENTE, cliente);
                startActivity(intent);

            } else {

                if (edt_nome.getText().toString().isEmpty()) {
                    edt_nome.setError("Nome obrigatório");
                }
                if (edt_data_nascimento.getText().toString().isEmpty()) {
                    edt_data_nascimento.setError("Data de nascimento obrigatório");
                }
                if (edt_cpf.getText().toString().isEmpty()) {
                    edt_cpf.setError("CPF obrigatório");
                }
                if (edt_rg.getText().toString().isEmpty()) {
                    edt_rg.setError("RG obrigatório");
                }
                if (edt_email.getText().toString().isEmpty()) {
                    edt_email.setError("E-mail obrigatório");
                }
                if (edt_celular.getText().toString().isEmpty()) {
                    edt_celular.setError("Celular obrigatório");
                }

            }
        });

    }

    void initialWork() {
        edt_nome = findViewById(R.id.txt_nome);
        edt_data_nascimento = findViewById(R.id.edt_data_nascimento);
        edt_cpf = findViewById(R.id.til_cpf);
        edt_rg = findViewById(R.id.txt_renda);
        edt_email = findViewById(R.id.txt_email);
        edt_celular = findViewById(R.id.txt_numero);
        btnContinuar = findViewById(R.id.btn_continuar);
    }

    public void voltar(View view) {
        finish();
    }
}