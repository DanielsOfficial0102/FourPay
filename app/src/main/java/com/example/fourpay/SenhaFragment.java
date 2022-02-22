package com.example.fourpay;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fourpay.activitys.EmprestimoConfirmarActivity;
import com.example.fourpay.activitys.PagamentoConfirmadoActivity;
import com.example.fourpay.activitys.PixDadosActivity;
import com.example.fourpay.activitys.RecargaValidacaoActivity;
import com.example.fourpay.activitys.TransferenciaActivity;
import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class SenhaFragment extends Fragment {

    private final String ConfirmacaoDadosActivityClass = "class com.example.fourpay.activitys.ConfirmacaoChaveActivity";
    private final String TransferenciaActivityClass = "class com.example.fourpay.activitys.TransferenciaActivity";
    private final String RecargaValidacaoActivityClass = "class com.example.fourpay.activitys.RecargaValidacaoActivity";
    private final String EmprestimoConfirmarActivityClass = "class com.example.fourpay.activitys.EmprestimoConfirmarActivity";

    private EditText edtSenha;
    private Button btnEnviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_senha, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEnviar = view.findViewById(R.id.btn_enviar);
        edtSenha = view.findViewById(R.id.edt_senha);

        btnEnviar.setOnClickListener(v -> {
            if (edtSenha.getText().toString().isEmpty())
                Toast.makeText(getContext(), "Digite a senha", Toast.LENGTH_SHORT).show();
            else {
                String senhaDigitada = edtSenha.getText().toString();
                FirebaseAuth mAuth = ConfiguracaoFirebase.getFirebaseInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                DatabaseReference buscaSenha = ConfiguracaoFirebase.getDataBaseReference().child("Usuarios").child(user.getUid()).child("conta").child("senhaCartao");
                String classe = requireContext().getClass().toString();

                buscaSenha.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (senhaDigitada.equals(snapshot.getValue().toString())) {
                            if (classe.equals(ConfirmacaoDadosActivityClass)) {
                                ConfiguracaoFirebase.setSaldo(PixDadosActivity.saldoAtual - PixDadosActivity.valorPix);
                                Intent intent = new Intent(getContext(), PagamentoConfirmadoActivity.class);
                                intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_PIX);
                                startActivity(intent);
                                requireActivity().finish();

                            } else if (classe.equals(TransferenciaActivityClass)) {
                                ConfiguracaoFirebase.setSaldo(TransferenciaActivity.saldoAtual - TransferenciaActivity.valorTransferir);
                                Intent intent = new Intent(getContext(), PagamentoConfirmadoActivity.class);
                                intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_PAGAR);
                                startActivity(intent);
                                requireActivity().finish();

                            } else if (classe.equals(RecargaValidacaoActivityClass)) {
                                Float saldo = RecargaValidacaoActivity.saldo;
                                Float valRecarga = RecargaValidacaoActivity.valRecarga;
                                ConfiguracaoFirebase.setSaldo(saldo - valRecarga);

                                Intent intent = new Intent(getContext(), PagamentoConfirmadoActivity.class);
                                intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_RECARGA);
                                startActivity(intent);
                                requireActivity().finish();

                            } else if (classe.equals(EmprestimoConfirmarActivityClass)) {
                                ConfiguracaoFirebase.setSaldo(EmprestimoConfirmarActivity.saldoAtual + EmprestimoConfirmarActivity.valorContratar);
                                Intent intent = new Intent(getContext(), PagamentoConfirmadoActivity.class);
                                intent.putExtra(PagamentoConfirmadoActivity.KEY_PAGAMENTO, PagamentoConfirmadoActivity.KEY_EMPRESTIMO);
                                startActivity(intent);
                                requireActivity().finish();
                            } else {
                                Toast.makeText(getContext(), "Erro ao selecionar activity", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(requireContext(), "Digite a senha novamente", Toast.LENGTH_SHORT).show();
                            edtSenha.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(requireContext(), "Erro ao validar senha", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}