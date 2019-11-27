package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PemesananKosDetail {

    public static final String TAG_ID ="id_pemesanan_kos";

    @SerializedName("id_pemesanan_kos")
    @Expose
    private String idPemesananKos;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("nama_kos")
    @Expose
    private String namaKos;
    @SerializedName("foto_kos")
    @Expose
    private String fotoKos;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("stok_kamar_kos")
    @Expose
    private String stokKamarKos;
    @SerializedName("jenis_kos")
    @Expose
    private String jenisKos;
    @SerializedName("alamat_kos")
    @Expose
    private String alamatKos;
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

    public String getIdPemesananKos() {
        return idPemesananKos;
    }

    public void setIdPemesananKos(String idPemesananKos) {
        this.idPemesananKos = idPemesananKos;
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

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public String getFotoKos() {
        return fotoKos;
    }

    public void setFotoKos(String fotoKos) {
        this.fotoKos = fotoKos;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStokKamarKos() {
        return stokKamarKos;
    }

    public void setStokKamarKos(String stokKamarKos) {
        this.stokKamarKos = stokKamarKos;
    }

    public String getJenisKos() {
        return jenisKos;
    }

    public void setJenisKos(String jenisKos) {
        this.jenisKos = jenisKos;
    }

    public String getAlamatKos() {
        return alamatKos;
    }

    public void setAlamatKos(String alamatKos) {
        this.alamatKos = alamatKos;
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