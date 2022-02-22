package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;

public class ScanQRCodeActivity extends AppCompatActivity {

    static final int CAMERA_PERMISSION_CODE = 1;

    private Camera mCamera;
    private CameraPreview mPreview;
    private Button digitarCodigo;
    private ImageView voltarTela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);
        requestCameraPermission();
        initialWork();


        digitarCodigo.setOnClickListener(view -> startActivity(new Intent(ScanQRCodeActivity.this, DigiteCodigoBarrasActivity.class)));
        voltarTela.setOnClickListener((View.OnClickListener) view -> startActivity(new Intent(ScanQRCodeActivity.this, HomeActivity.class)));

    }

    private void initialWork() {
        digitarCodigo = findViewById(R.id.digitar_Codigo);
        voltarTela = findViewById(R.id.voltar);
    }

    public boolean isCameraPermissionGranted() {
        return ActivityCompat.checkSelfPermission(ScanQRCodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão OK", Toast.LENGTH_SHORT).show();
                openCamera();
            } else {
                Toast.makeText(this, "Permissão obrigatória", Toast.LENGTH_SHORT).show();
                requestCameraPermission();
            }
        }
    }

    private void openCamera() {
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.previeww);
        preview.addView(mPreview);
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(
                ScanQRCodeActivity.this,
                new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    public void voltar(View view) {
        finish();
    }

    public void abrirCodigoDeBarras(View view) {
        startActivity(new Intent(ScanQRCodeActivity.this, DigiteCodigoBarrasActivity.class));
        finish();
    }
}