package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fourpay.R;

public class EmprestimoActivity extends AppCompatActivity {

    private Spinner parcelas;
    private String parcelaSelect;
    private String dataSelect;
    private Spinner datas;
    private Float valorSimulado;
    private EditText valorSimular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestimo);
        initializerWork();

        Button btnAcessar = findViewById(R.id.btnAcessar);
        btnAcessar.setOnClickListener(view -> {

            dataSelect = datas.getSelectedItem().toString();
            parcelaSelect = parcelas.getSelectedItem().toString();
            valorSimulado = Float.parseFloat(valorSimular.getText().toString());

            if (!dataSelect.isEmpty() && !parcelaSelect.isEmpty() && !valorSimular.getText().toString().isEmpty()) {

                if (Float.parseFloat(valorSimular.getText().toString()) > 5000f)
                    valorSimular.setError("Valor indisponível");
                else {
                    Intent intent = new Intent(this, EmprestimoConfirmarActivity.class);
                    intent.putExtra(EmprestimoConfirmarActivity.KEY_DIA_PAGAMENTO, dataSelect);
                    intent.putExtra(EmprestimoConfirmarActivity.KEY_PARCELAS, parcelaSelect);
                    intent.putExtra(EmprestimoConfirmarActivity.KEY_VALOR_SIMULADO, valorSimulado);
                    startActivity(intent);
                }
            } else {

                if (dataSelect.isEmpty()) {
                    ((TextView) datas.getSelectedView()).setError("Selecione um campo");
                }
                if (parcelaSelect.isEmpty()) {
                    ((TextView) parcelas.getSelectedView()).setError("Selecione um campo");
                }
                if (valorSimular.getText().toString().isEmpty()) {
                    valorSimular.setError("Preencha o campo");
                }
            }
        });
    }

    public void initializerWork() {
        parcelas = findViewById(R.id.parcelas);
        datas = findViewById(R.id.melhor_data);
        valorSimular = findViewById(R.id.valor_simular);

    }

    public void voltar(View view) {
        finish();
    }
}