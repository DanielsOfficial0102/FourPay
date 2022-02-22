package com.example.fourpay.activitys;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fourpay.R;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;


public class RecargaActivity extends AppCompatActivity {
    private Spinner recarga;
    private Spinner operadora;
    private Spinner pagamento;
    private EditText celular;
    private Button continuar;
    private AppCompatAutoCompleteTextView tvSaldo;
    private DatabaseReference buscaSaldo;
    private Float saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recarga);
        initializerWork();
        buscaSaldo();

        continuar.setOnClickListener(view -> {

            String recargaSelect = recarga.getSelectedItem().toString();
            String pagamentoSelect = pagamento.getSelectedItem().toString();
            String operadoraSelect = operadora.getSelectedItem().toString();
            String numCelular = celular.getText().toString();


            if (!recargaSelect.isEmpty() && !pagamentoSelect.isEmpty() && !operadoraSelect.isEmpty() && !numCelular.isEmpty() && Patterns.PHONE.matcher(numCelular).matches()) {
                Intent intent = new Intent(this, RecargaValidacaoActivity.class);
                intent.putExtra(RecargaValidacaoActivity.KEY_NUMERO, numCelular);
                intent.putExtra(RecargaValidacaoActivity.KEY_PAGAMENTO, pagamentoSelect);
                intent.putExtra(RecargaValidacaoActivity.KEY_OPERADORA, operadoraSelect);
                intent.putExtra(RecargaValidacaoActivity.KEY_VALOR, recargaSelect);

                startActivity(intent);
            } else {


                if (numCelular.isEmpty()) {
                    celular.setError("Preencha o campo");

                }
                if (!Patterns.PHONE.matcher(numCelular).matches()) {
                    celular.setError("Preencha o campo");

                }
                if (recargaSelect.isEmpty()) {
                    ((TextView) recarga.getSelectedView()).setError("Selecione um dos campos");
                }
                if (pagamentoSelect.isEmpty()) {
                    ((TextView) pagamento.getSelectedView()).setError("Selecione um dos campos");

                }
                if (operadoraSelect.isEmpty()) {
                    ((TextView) operadora.getSelectedView()).setError("Selecione um dos campos");

                }
                ;

            }
        });

    }

    public void initializerWork() {
        recarga = findViewById(R.id.recarga);
        operadora = findViewById(R.id.operadora);
        pagamento = findViewById(R.id.pagamento);
        celular = findViewById(R.id.txtTelefone);
        continuar = findViewById(R.id.btnAcessar);
        tvSaldo = findViewById(R.id.tvSaldo);
    }

    private void buscaSaldo() {
        buscaSaldo = ConfiguracaoFirebase.getSaldo();
        buscaSaldo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String saldoUser = snapshot.getValue().toString();
                saldo = Float.parseFloat(saldoUser);
                DecimalFormat mask = new DecimalFormat("#,##0.00");
                String saldoMostra = mask.format(saldo);
                tvSaldo.setText(saldoMostra);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                String mensagem = ("Erro ao buscar saldo");
            }
        });
    }

    public void voltar(View view) {
        finish();
    }
}