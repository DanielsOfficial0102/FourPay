package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fourpay.R;

public class SplashActivity extends AppCompatActivity {

    private TextView txtFour, txtPay;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initialWork();

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_screen);
        txtFour.setAnimation(anim);
        txtPay.setAnimation(anim);
        img.setAnimation(anim);

        //Contagem da SplashActivity
        new Handler().postDelayed((Runnable) () -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 4000);
    }

    private void initialWork() {
        txtFour = findViewById(R.id.text1);
        txtPay = findViewById(R.id.txt_nome);
        img = findViewById(R.id.logo_img);
    }
}