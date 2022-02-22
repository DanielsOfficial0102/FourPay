package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fourpay.R;

public class PixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pix);
    }

    public void abrirTransferenciaPix(View view) {
        startActivity(new Intent(PixActivity.this, TransferenciaPixActivity.class));
    }

    public void abrirMinhasChaves(View view) {
        startActivity(new Intent(PixActivity.this, MinhasChavesPixActivity.class));
    }

    public void voltar(View view) {
        finish();
    }
}