package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KontrakanPemilik {

    @SerializedName("id_kontrakan")
    @Expose
    private String idKontrakan;
    @SerializedName("nama_kontrakan")
    @Expose
    private String namaKontrakan;
    @SerializedName("deskripsi_kontrakan")
    @Expose
    private String deskripsiKontrakan;
    @SerializedName("foto_kontrakan")
    @Expose
    private String fotoKontrakan;
    @SerializedName("foto_kontrakan_2")
    @Expose
    private String fotoKontrakan2;
    @SerializedName("foto_kontrakan_3")
    @Expose
    private String fotoKontrakan3;
    @SerializedName("waktu_post")
    @Expose
    private String waktuPost;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("altitude")
    @Expose
    private String altitude;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("alamat_kontrakan")
    @Expose
    private String alamatKontrakan;
    @SerializedName("id_pemilik")
    @Expose
    private String idPemilik;
    @SerializedName("nama_lengkap_pemilik")
    @Expose
    private String namaLengkapPemilik;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_kk")
    @Expose
    private String noKk;
    @SerializedName("no_handphone_pemilik")
    @Expose
    private String noHandphonePemilik;
    @SerializedName("foto_pemilik")
    @Expose
    private String fotoPemilik;
    @SerializedName("alamat_pemilik")
    @Expose
    private String alamatPemilik;

    public String getIdKontrakan() {
        return idKontrakan;
    }

    public void setIdKontrakan(String idKontrakan) {
        this.idKontrakan = idKontrakan;
    }

    public String getNamaKontrakan() {
        return namaKontrakan;
    }

    public void setNamaKontrakan(String namaKontrakan) {
        this.namaKontrakan = namaKontrakan;
    }

    public String getDeskripsiKontrakan() {
        return deskripsiKontrakan;
    }

    public void setDeskripsiKontrakan(String deskripsiKontrakan) {
        this.deskripsiKontrakan = deskripsiKontrakan;
    }

    public String getFotoKontrakan() {
        return fotoKontrakan;
    }

    public void setFotoKontrakan(String fotoKontrakan) {
        this.fotoKontrakan = fotoKontrakan;
    }

    public String getFotoKontrakan2() {
        return fotoKontrakan2;
    }

    public void setFotoKontrakan2(String fotoKontrakan2) {
        this.fotoKontrakan2 = fotoKontrakan2;
    }

    public String getFotoKontrakan3() {
        return fotoKontrakan3;
    }

    public void setFotoKontrakan3(String fotoKontrakan3) {
        this.fotoKontrakan3 = fotoKontrakan3;
    }

    public String getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(String waktuPost) {
        this.waktuPost = waktuPost;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getAlamatKontrakan() {
        return alamatKontrakan;
    }

    public void setAlamatKontrakan(String alamatKontrakan) {
        this.alamatKontrakan = alamatKontrakan;
    }

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
    }

    public String getNamaLengkapPemilik() {
        return namaLengkapPemilik;
    }

    public void setNamaLengkapPemilik(String namaLengkapPemilik) {
        this.namaLengkapPemilik = namaLengkapPemilik;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoKk() {
        return noKk;
    }

    public void setNoKk(String noKk) {
        this.noKk = noKk;
    }

    public String getNoHandphonePemilik() {
        return noHandphonePemilik;
    }

    public void setNoHandphonePemilik(String noHandphonePemilik) {
        this.noHandphonePemilik = noHandphonePemilik;
    }

    public String getFotoPemilik() {
        return fotoPemilik;
    }

    public void setFotoPemilik(String fotoPemilik) {
        this.fotoPemilik = fotoPemilik;
    }

    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

}
