package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fourpay.R;

public class PagamentoConfirmadoActivity extends AppCompatActivity {

    public static final String KEY_PAGAMENTO = "PAGAMENTO";
    public static final String KEY_AGENDAR = "AGENDAR";
    public static final String KEY_PAGAR = "AGORA";
    public static final String KEY_PIX = "PIX";
    public static final String KEY_RECARGA = "RECARGA";
    public static final String KEY_EMPRESTIMO = "EMPRESTIMO";
    public static final String KEY_DEPOSITO = "DEPOSITO";

    private TextView txtResultado;
    private ImageView img;
    private Button btnComprovante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_confirmado);

        initialWork();

        Intent getIntent = getIntent();
        String pagamento = getIntent.getStringExtra(KEY_PAGAMENTO);

        if (pagamento.equals(KEY_AGENDAR))
            txtResultado.setText("Pagamento agendado com sucesso");
        else if (pagamento.equals(KEY_PAGAR))
            txtResultado.setText(getString(R.string.pagamento_realizado));
        else if (pagamento.equals(KEY_PIX))
            txtResultado.setText("Pix realizado com sucesso");
        else if (pagamento.equals(KEY_RECARGA))
            txtResultado.setText("Recarga efetuada");
        else if (pagamento.equals(KEY_EMPRESTIMO))
            txtResultado.setText("Emprestimo realizado com sucesso");
        else if (pagamento.equals(KEY_DEPOSITO))
            txtResultado.setText("Boleto de depósito enviado para seu e-mail");

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_from_pagamento);
        img.setImageResource(R.drawable.sucesso_transferencia);
        btnComprovante.setVisibility(View.VISIBLE);
        img.setAnimation(anim);

    }

    private void initialWork() {
        img = findViewById(R.id.img);
        txtResultado = findViewById(R.id.txt_resultado_pagamento);
        btnComprovante = findViewById(R.id.btn_comprovante);
    }


    public void voltarParaHome(View view) {
        startActivity(new Intent(PagamentoConfirmadoActivity.this, HomeActivity.class));
    }
}