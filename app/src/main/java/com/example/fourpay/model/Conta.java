package com.example.fourpay.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Conta implements Parcelable{
    private Integer senhaAplicativo, senhaCartao;
    private Float saldo;

    public Conta() {
    }

    protected Conta(Parcel in) {
        if (in.readByte() == 0) {
            senhaAplicativo = null;
        } else {
            senhaAplicativo = in.readInt();
        }
        if (in.readByte() == 0) {
            senhaCartao = null;
        } else {
            senhaCartao = in.readInt();
        }
        if (in.readByte() == 0) {
            saldo = null;
        } else {
            saldo = in.readFloat();
        }
    }

    public static final Creator<Conta> CREATOR = new Creator<Conta>() {
        @Override
        public Conta createFromParcel(Parcel in) {
            return new Conta(in);
        }

        @Override
        public Conta[] newArray(int size) {
            return new Conta[size];
        }
    };

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Integer getSenhaAplicativo() {
        return senhaAplicativo;
    }

    public void setSenhaAplicativo(Integer senhaAplicativo) {
        this.senhaAplicativo = senhaAplicativo;
    }

    public Integer getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(Integer senhaCartao) {
        this.senhaCartao = senhaCartao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (senhaAplicativo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(senhaAplicativo);
        }
        if (senhaCartao == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(senhaCartao);
        }
        if (saldo == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeFloat(saldo);
        }
    }
}
