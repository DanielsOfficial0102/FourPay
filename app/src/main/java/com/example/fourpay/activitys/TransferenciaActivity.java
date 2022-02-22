package com.example.fourpay.activitys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fourpay.R;
import com.example.fourpay.SenhaFragment;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class TransferenciaActivity extends AppCompatActivity {
    private AppCompatAutoCompleteTextView saldo;
    private Button btnContinuar;
    private Spinner bancos;
    private EditText agencia;
    private EditText conta;
    private RadioButton outroTitular;
    private RadioButton mesmoTitular;
    private EditText destinatario;
    private EditText cpfCnpj;
    private EditText infValor;
    public static Float saldoAtual;
    public static Float valorTransferir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);
        initializerWork();

        ConfiguracaoFirebase.getSaldo().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String temp = snapshot.getValue().toString();
                DecimalFormat mask = new DecimalFormat("#,##0.00");
                String saldoMostra = mask.format(Float.parseFloat(temp));
                saldo.setText(saldoMostra);
                saldoAtual = Float.parseFloat(temp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TransferenciaActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        outroTitular.setTextColor(ActivityCompat.getColor(this, R.color.azul_medio));

        btnContinuar.setOnClickListener(view -> {
            if (!bancos.getSelectedItem().toString().isEmpty() && !infValor.getText().toString().isEmpty() &&
                    !agencia.getText().toString().isEmpty() &&
                    !conta.getText().toString().isEmpty() &&
                    outroTitular.isChecked() || mesmoTitular.isChecked() &&
                    !destinatario.getText().toString().isEmpty() &&
                    !cpfCnpj.getText().toString().isEmpty() ) {

                valorTransferir = Float.parseFloat(infValor.getText().toString());
                if(valorTransferir <= saldoAtual)
                    replaceFragment(new SenhaFragment());
                else
                    Toast.makeText(this, "Saldo indisponível", Toast.LENGTH_SHORT).show();

            } else {

                if (infValor.getText().toString().isEmpty()) {
                    infValor.setError("Preencha o campo");
                }

                if (bancos.getSelectedItem().toString().isEmpty()) {
                    ((TextView) bancos.getSelectedView()).setError("Selecione um dos campos");
                }

                if (conta.getText().toString().isEmpty()) {
                    conta.setError("Preencha o campo");
                }

                if (agencia.getText().toString().isEmpty()) {
                    agencia.setError("Preencha o campo");
                }

                if (destinatario.getText().toString().isEmpty()) {
                    destinatario.setError("Preencha o campo");
                }
                if (cpfCnpj.getText().toString().isEmpty()) {
                    cpfCnpj.setError("Preencha o campo");
                }

                if (!outroTitular.isChecked() && !mesmoTitular.isChecked()) {
                    outroTitular.setError("Selecione um dos campos");
                    mesmoTitular.setError("Selecione um dos campos");
                }

            }

        });

        outroTitular.setOnClickListener(view -> {
            outroTitular.setTextColor(ActivityCompat.getColor(this, R.color.azul_medio));
            mesmoTitular.setTextColor(ActivityCompat.getColor(this, R.color.cinza));

        });
        mesmoTitular.setOnClickListener(view -> {
            mesmoTitular.setTextColor(ActivityCompat.getColor(this, R.color.azul_medio));
            outroTitular.setTextColor(ActivityCompat.getColor(this, R.color.cinza));

        });

    }

    public void initializerWork() {
        btnContinuar = findViewById(R.id.btnContinuar);
        bancos = findViewById(R.id.spn_bancos);
        agencia = findViewById(R.id.edt_agencia);
        conta = findViewById(R.id.edt_conta);
        outroTitular = findViewById(R.id.outro_titular);
        mesmoTitular = findViewById(R.id.mesmo_titular);
        destinatario = findViewById(R.id.edt_destinatario);
        cpfCnpj = findViewById(R.id.edt_cpf_cpnj);
        infValor = findViewById(R.id.inf_valor);
        saldo = findViewById(R.id.tvSaldo);
    }

    private void replaceFragment(SenhaFragment senhaFragment) {
        btnContinuar.setVisibility(View.INVISIBLE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, senhaFragment);
        fragmentTransaction.commit();
    }

    public void voltar(View view) {
        finish();
    }


}