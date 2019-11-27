package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KosPemilik {

    @SerializedName("id_kos")
    @Expose
    private String idKos;
    @SerializedName("nama_kos")
    @Expose
    private String namaKos;
    @SerializedName("deskripsi_kos")
    @Expose
    private String deskripsiKos;
    @SerializedName("foto_kos")
    @Expose
    private String fotoKos;
    @SerializedName("foto_kos_2")
    @Expose
    private String fotoKos2;
    @SerializedName("foto_kos_3")
    @Expose
    private String fotoKos3;
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
    @SerializedName("alamat_kos")
    @Expose
    private String alamatKos;
    @SerializedName("jenis_kos")
    @Expose
    private String jenisKos;
    @SerializedName("stok_kamar")
    @Expose
    private String stokKamar;
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

    public String getIdKos() {
        return idKos;
    }

    public void setIdKos(String idKos) {
        this.idKos = idKos;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public String getDeskripsiKos() {
        return deskripsiKos;
    }

    public void setDeskripsiKos(String deskripsiKos) {
        this.deskripsiKos = deskripsiKos;
    }

    public String getFotoKos() {
        return fotoKos;
    }

    public void setFotoKos(String fotoKos) {
        this.fotoKos = fotoKos;
    }

    public String getFotoKos2() {
        return fotoKos2;
    }

    public void setFotoKos2(String fotoKos2) {
        this.fotoKos2 = fotoKos2;
    }

    public String getFotoKos3() {
        return fotoKos3;
    }

    public void setFotoKos3(String fotoKos3) {
        this.fotoKos3 = fotoKos3;
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

    public String getAlamatKos() {
        return alamatKos;
    }

    public void setAlamatKos(String alamatKos) {
        this.alamatKos = alamatKos;
    }

    public String getJenisKos() {
        return jenisKos;
    }

    public void setJenisKos(String jenisKos) {
        this.jenisKos = jenisKos;
    }

    public String getStokKamar() {
        return stokKamar;
    }

    public void setStokKamar(String stokKamar) {
        this.stokKamar = stokKamar;
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