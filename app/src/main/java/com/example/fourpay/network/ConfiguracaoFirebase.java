package com.example.fourpay.network;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {
    private static DatabaseReference databaseReference = null;
    private static FirebaseAuth firebaseAuth = null;
    private static DatabaseReference saldo = null;
    private static FirebaseUser currentuser = null;
    private static DatabaseReference currentClient = null;
    private static DatabaseReference nome = null;

    public static DatabaseReference getDataBaseReference() {
        if(databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }
    public static FirebaseAuth getFirebaseInstance() {
        if(firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static FirebaseUser getUser() {
        if(currentuser == null)
            currentuser = getFirebaseInstance().getCurrentUser();
        return currentuser;
    }

    public static DatabaseReference getSaldo() {
        if(saldo == null)
            saldo = getDataBaseReference().child("Usuarios").child(getUser().getUid()).child("conta").child("saldo");
        return saldo;
    }

    public static void setSaldo(Float saldo) {
        getSaldo().setValue(saldo);
    }

    public static DatabaseReference getNome() {
        if(nome == null)
            nome = getDataBaseReference().child("Usuarios").child(getUser().getUid()).child("nome");
        return nome;
    }

    public static DatabaseReference getCurrentClient() {
        if(currentClient == null)
            currentClient = getDataBaseReference().child("Usuarios").child(getUser().getUid());
        return currentClient;
    }
}
