package com.example.fourpay.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Endereço implements Parcelable {
    private String numeroCasa, rua, bairro, cep, complemento, cidade, estado;

    public Endereço() {
    }

    public Endereço(String numeroCasa, String rua, String bairro, String cep, String complemento, String cidade, String estado) {
        this.numeroCasa = numeroCasa;
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    protected Endereço(Parcel in) {
        numeroCasa = in.readString();
        rua = in.readString();
        bairro = in.readString();
        cep = in.readString();
        complemento = in.readString();
        cidade = in.readString();
        estado = in.readString();
    }

    public static final Creator<Endereço> CREATOR = new Creator<Endereço>() {
        @Override
        public Endereço createFromParcel(Parcel in) {
            return new Endereço(in);
        }

        @Override
        public Endereço[] newArray(int size) {
            return new Endereço[size];
        }
    };

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(numeroCasa);
        parcel.writeString(rua);
        parcel.writeString(bairro);
        parcel.writeString(cep);
        parcel.writeString(complemento);
        parcel.writeString(cidade);
        parcel.writeString(estado);
    }
}
