package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fourpay.R;

public class ComprovantePagamentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante_pagamento);
    }

    public void irParaHome(View view) {
        startActivity(new Intent(ComprovantePagamentoActivity.this, HomeActivity.class));
    }
}