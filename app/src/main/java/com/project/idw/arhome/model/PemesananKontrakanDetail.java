package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PemesananKontrakanDetail {

    public static final String TAG_ID ="id_pemesanan_kontrakan";

    @SerializedName("id_pemesanan_kontrakan")
    @Expose
    private String idPemesananKontrakan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("nama_kontrakan")
    @Expose
    private String namaKontrakan;
    @SerializedName("foto_kontrakan")
    @Expose
    private String fotoKontrakan;
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
    @SerializedName("no_handphone_pemilik")
    @Expose
    private String noHandphonePemilik;
    @SerializedName("foto_pemilik")
    @Expose
    private String fotoPemilik;
    @SerializedName("id_pengguna")
    @Expose
    private String idPengguna;
    @SerializedName("nama_lengkap_pengguna")
    @Expose
    private String namaLengkapPengguna;
    @SerializedName("no_handphone_pengguna")
    @Expose
    private String noHandphonePengguna;
    @SerializedName("foto_pengguna")
    @Expose
    private String fotoPengguna;

    public String getIdPemesananKontrakan() {
        return idPemesananKontrakan;
    }

    public void setIdPemesananKontrakan(String idPemesananKontrakan) {
        this.idPemesananKontrakan = idPemesananKontrakan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getNamaKontrakan() {
        return namaKontrakan;
    }

    public void setNamaKontrakan(String namaKontrakan) {
        this.namaKontrakan = namaKontrakan;
    }

    public String getFotoKontrakan() {
        return fotoKontrakan;
    }

    public void setFotoKontrakan(String fotoKontrakan) {
        this.fotoKontrakan = fotoKontrakan;
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

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaLengkapPengguna() {
        return namaLengkapPengguna;
    }

    public void setNamaLengkapPengguna(String namaLengkapPengguna) {
        this.namaLengkapPengguna = namaLengkapPengguna;
    }

    public String getNoHandphonePengguna() {
        return noHandphonePengguna;
    }

    public void setNoHandphonePengguna(String noHandphonePengguna) {
        this.noHandphonePengguna = noHandphonePengguna;
    }

    public String getFotoPengguna() {
        return fotoPengguna;
    }

    public void setFotoPengguna(String fotoPengguna) {
        this.fotoPengguna = fotoPengguna;
    }

}