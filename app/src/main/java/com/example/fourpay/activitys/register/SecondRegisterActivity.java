package com.example.fourpay.activitys.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.model.Endereço;
import com.example.fourpay.model.Cliente;
import com.google.android.material.textfield.TextInputEditText;

public class SecondRegisterActivity extends AppCompatActivity {

    private Button btnContinuar;
    private TextInputEditText edtCep;
    private TextInputEditText edtEndereco;
    private TextInputEditText edtBairro;
    private TextInputEditText edtCidade;
    private TextInputEditText edtEstado;
    private TextInputEditText edtNumeroCasa;
    private TextInputEditText edtComplemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_register);

        initialWork();
        Intent getIntent = getIntent();
        Cliente cliente = getIntent.getParcelableExtra(FifthRegisterActivity.KEY_CLIENTE);

        btnContinuar.setOnClickListener(view -> {

            if (!edtCep.getText().toString().isEmpty() &&
                    !edtEndereco.getText().toString().isEmpty() &&
                    !edtBairro.getText().toString().isEmpty() &&
                    !edtCidade.getText().toString().isEmpty() &&
                    !edtEstado.getText().toString().isEmpty() &&
                    !edtNumeroCasa.getText().toString().isEmpty()){

                Endereço endereço = new Endereço();
                endereço.setCep(edtCep.getText().toString());
                endereço.setRua(edtEndereco.getText().toString());
                endereço.setBairro(edtBairro.getText().toString());
                endereço.setCidade(edtCidade.getText().toString());
                endereço.setEstado(edtEstado.getText().toString());
                endereço.setNumeroCasa(edtNumeroCasa.getText().toString());

                cliente.setEndereço(endereço);
                Intent intent = new Intent(this, ThirdRegisterActivity.class);
                intent.putExtra(FifthRegisterActivity.KEY_CLIENTE, cliente);
                startActivity(intent);

            } else {

                if (edtCep.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo CEP é obrigatório", Toast.LENGTH_SHORT).show();
                }
                if (edtEndereco.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo Endereço é obrigatório", Toast.LENGTH_SHORT).show();
                }
                if (edtBairro.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo Bairro é obrigatório", Toast.LENGTH_SHORT).show();
                }
                if (edtCidade.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo Cidade é obrigatório", Toast.LENGTH_SHORT).show();
                }
                if (edtEstado.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo Estado é obrigatório", Toast.LENGTH_SHORT).show();
                }
                if (edtNumeroCasa.getText().toString().isEmpty()) {
                    Toast.makeText(this, "O campo Número é obrigatório", Toast.LENGTH_SHORT).show();
                }

                }
        });

    }

    public void voltar(View view) {
        finish();
    }

    void initialWork (){
        edtCep = findViewById(R.id.edt_cep);
        edtEndereco = findViewById(R.id.edt_endereco);
        edtBairro = findViewById(R.id.edt_bairro);
        edtCidade = findViewById(R.id.edt_cidade);
        edtEstado = findViewById(R.id.edt_estado);
        edtNumeroCasa = findViewById(R.id.edt_numero_casa);
        btnContinuar = findViewById(R.id.btn_continuar);
        edtComplemento = findViewById(R.id.edt_complemento);
    }
}