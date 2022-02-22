package com.example.fourpay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.fourpay.network.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Cliente implements Parcelable {
    private String id, nome, cpf, rg, email, celular, dataNascimento, estadoCivil;
    private Float renda;
    private Endereço endereço;
    private Conta conta;

    public Cliente() {
    }

    protected Cliente(Parcel in) {
        id = in.readString();
        nome = in.readString();
        cpf = in.readString();
        rg = in.readString();
        email = in.readString();
        celular = in.readString();
        dataNascimento = in.readString();
        estadoCivil = in.readString();
        if (in.readByte() == 0) {
            renda = null;
        } else {
            renda = in.readFloat();
        }
        endereço = in.readParcelable(Endereço.class.getClassLoader());
        conta = in.readParcelable(Conta.class.getClassLoader());
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public void salvarDados() {
        DatabaseReference database = ConfiguracaoFirebase.getDataBaseReference();
        this.conta.setSaldo(0f);
        database.child("Usuarios")
                .child(this.id)
                .setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Float getRenda() {
        return renda;
    }

    public void setRenda(Float renda) {
        this.renda = renda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereço getEndereço() {
        return endereço;
    }

    public void setEndereço(Endereço endereço) {
        this.endereço = endereço;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nome);
        parcel.writeString(cpf);
        parcel.writeString(rg);
        parcel.writeString(email);
        parcel.writeString(celular);
        parcel.writeString(dataNascimento);
        parcel.writeString(estadoCivil);
        if (renda == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(renda);
        }
        parcel.writeParcelable(endereço, i);
        parcel.writeParcelable(conta, i);
    }
}
