package com.example.fourpay.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.fourpay.R;

import java.util.Calendar;
import java.util.Date;

public class ConfirmarPagamentoActivity extends AppCompatActivity {

    private RadioButton pagarHoje;
    private RadioButton agendarPara;
    private TextView data;
    private TextView dataAgendada;
    private Button btnContinuar;
    private boolean validacaoData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pagamento);
        initializer();
        obterDataAtual();

        pagarHoje.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pagarHoje.isChecked()) {
                    Intent intent = new Intent(ConfirmarPagamentoActivity.this, PagamentoConfirmadoActivity.class);
                    intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_PAGAR);
                    startActivity(intent);
                } else if (agendarPara.isChecked() && validacaoData) {
                    Intent intent = new Intent(ConfirmarPagamentoActivity.this, PagamentoConfirmadoActivity.class);
                    intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_AGENDAR);
                    startActivity(intent);
                }
            }
        });


        pagarHoje.setOnClickListener(view -> {
            if (pagarHoje.isChecked()) {
                pagarHoje.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));
                data.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));
                agendarPara.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.cinza));
                dataAgendada.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.cinza));
                dataAgendada.setText("//");
                agendarPara.setChecked(false);
                dataAgendada.setError(null);
                dataAgendada.clearFocus();


                dataAgendada.setOnClickListener(view12 -> {
                    // Nao deixa colocar data caso nao esteja checado em agendar.
                });

            }
        });

        agendarPara.setOnClickListener(view -> {

            if (agendarPara.isChecked()) {

                agendarPara.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));
                abrirCalendario();
                dataAgendada.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));
                pagarHoje.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.cinza));
                data.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.cinza));

                dataAgendada.setOnClickListener(view1 -> {
                    abrirCalendario();

                });
            }
        });
    }

    public void obterDataAtual() {
        Calendar calAtual = Calendar.getInstance();
        int anoAtual = calAtual.get(Calendar.YEAR);
        int mesAtual = calAtual.get(Calendar.MONTH);
        int diaAtual = calAtual.get(Calendar.DAY_OF_MONTH);

        String fecha1 = diaAtual + "/0" + (mesAtual + 1) + "/" + anoAtual;

        data.setText(fecha1);
        data.setTextColor(ActivityCompat.getColor(ConfirmarPagamentoActivity.this, R.color.azul_medio));
    }


    public void initializer() {
        pagarHoje = findViewById(R.id.pagarHoje);
        btnContinuar = findViewById(R.id.btn_continuar);
        agendarPara = findViewById(R.id.agendarPara);
        data = findViewById(R.id.txt_data);
        dataAgendada = findViewById(R.id.txt_data_agendar);
    }

    public boolean verificarData(Date dataAtual, Date dataSelecionada) {
        if (dataAtual.compareTo(dataSelecionada) == 0 || dataAtual.compareTo(dataSelecionada) > 0) {
            validacaoData = false;
            return false;
        } else {
            validacaoData = true;
            return true;
        }
    }

    public void abrirCalendario() {
        Calendar cal = Calendar.getInstance();
        int anoAtual = cal.get(Calendar.YEAR);
        int mesAtual = cal.get(Calendar.MONTH);
        int diaAtual = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(ConfirmarPagamentoActivity.this, (datePicker, anoSelecionado, mesSelecionado, diaSelecionado) -> {
            String fecha = diaSelecionado + "/0" + (mesSelecionado + 1) + "/" + anoSelecionado;
            dataAgendada.setText(fecha);

            Date dataAtual = new Date(anoAtual, mesAtual, diaAtual);
            Date dataSelecionada = new Date(anoSelecionado, mesSelecionado, diaSelecionado);

            if (verificarData(dataAtual, dataSelecionada)) {
                dataAgendada.setError(null);
                dataAgendada.clearFocus();
            } else {
                dataAgendada.requestFocus();
                dataAgendada.setError("Data invalida");
            }
        }, anoAtual, mesAtual, diaAtual);
        dpd.show();
    }

    public void voltarParaHome(View view) {
        startActivity(new Intent(ConfirmarPagamentoActivity.this, HomeActivity.class));
    }
}

