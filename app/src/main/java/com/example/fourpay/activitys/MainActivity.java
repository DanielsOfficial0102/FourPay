package com.example.fourpay.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.fourpay.R;
import com.example.fourpay.activitys.register.FirstRegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FullScreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void abrirLogin(View view) {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void abrirCadastro(View view) {
        startActivity(new Intent(MainActivity.this, FirstRegisterActivity.class));
    }
}