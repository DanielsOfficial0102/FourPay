package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fourpay.R;
import com.example.fourpay.activitys.animations.LoadingDialog;
import com.example.fourpay.network.ConfiguracaoFirebase;

import de.hdodenhof.circleimageview.CircleImageView;

public class ValidarFotoActivity extends AppCompatActivity {

    static final String IMAGE_KEY = "IMAGE_BITMAP";

    private CircleImageView photo;
    private TextView txtResultado;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar_foto);

        initialWork();
        LoadingDialog dialog = new LoadingDialog(ValidarFotoActivity.this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        Bitmap bitmapPhoto = intent.getParcelableExtra(IMAGE_KEY);

        if(bitmapPhoto != null) {
            photo.setImageBitmap(bitmapPhoto);
            dialog.startLoadingDialog();
        }

        new Handler().postDelayed((Runnable) () -> {
            txtResultado.setText(getString(R.string.foto_validada));
            txtResultado.setVisibility(View.VISIBLE);
            btnContinuar.setText(getString(R.string.continuar));
            btnContinuar.setVisibility(View.VISIBLE);
            dialog.dismissDialog();
        },3000);

        btnContinuar.setOnClickListener(view -> {
            startActivity(new Intent(ValidarFotoActivity.this, HomeActivity.class));
            finish();
        });

    }

    private void initialWork() {
        photo = findViewById(R.id.foto);
        txtResultado = findViewById(R.id.txt_resultado);
        btnContinuar = findViewById(R.id.btn_continuar);
    }

    public void voltar(View view) {
        finish();
    }
}