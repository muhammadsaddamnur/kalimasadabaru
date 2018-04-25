package com.saddam.nur.kalimasadabaru.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("no_rek")
    @Expose
    private String noRek;
    @SerializedName("atas_nama")
    @Expose
    private String atas_nama;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNoRek() {
        return noRek;
    }

    public void setNoRek(String noRek) {
        this.noRek = noRek;
    }

    public String getAtas_nama() {
        return atas_nama;
    }

    public void setAtas_nama(String atasnama) {
        this.atas_nama = atasnama;
    }
}

