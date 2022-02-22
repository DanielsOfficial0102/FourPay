package com.example.fourpay.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.activitys.animations.LoadingDialog;

import de.hdodenhof.circleimageview.CircleImageView;

public class FotoActivity extends AppCompatActivity {
    static final int CAMERA_PERMISSION_CODE = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_GET = 1;

    private Button btnContinuar, btnCamera, btnGaleria;
    private CircleImageView foto;

    private Bitmap bitmapPhoto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        initialWork();

        btnCamera.setOnClickListener(view -> {
            if (isCameraPermissionGranted())
                dispararIntentCamera();
            else
                requestCameraPermission();
        });

        btnGaleria.setOnClickListener(view -> {
            selectImage();
        });

        btnContinuar.setOnClickListener(view -> {
            if (bitmapPhoto == null)
                Toast.makeText(this, "É necessária uma imagem com seu rosto", Toast.LENGTH_SHORT).show();
            else {
                Intent intent = new Intent(FotoActivity.this, ValidarFotoActivity.class);
                intent.putExtra(ValidarFotoActivity.IMAGE_KEY, bitmapPhoto);
                startActivity(intent);
            }
        });
    }

    private void initialWork() {
        btnContinuar = findViewById(R.id.btn_continuar);
        btnCamera = findViewById(R.id.btn_camera);
        btnGaleria = findViewById(R.id.btn_galeria);
        foto = findViewById(R.id.foto);
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(
                FotoActivity.this,
                new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
    }

    private void dispararIntentCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmapPhoto = (Bitmap) extras.get("data");
            foto.setPadding(3, 3, 3, 3);
            btnContinuar.setVisibility(View.VISIBLE);
            foto.setImageBitmap(bitmapPhoto);
        } else if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            //Bitmap thumbnail = data.getParcelableExtra("data");
            //Uri fullPhotoUri = data.getData();
            //foto.setImageBitmap(thumbnail);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permissão OK", Toast.LENGTH_SHORT).show();
                dispararIntentCamera();
            } else {
                Toast.makeText(this, "Permissão obrigatória", Toast.LENGTH_SHORT).show();
                requestCameraPermission();
            }
        }
    }

    public boolean isCameraPermissionGranted() {
        return ActivityCompat.checkSelfPermission(FotoActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GET);
        }
    }
}