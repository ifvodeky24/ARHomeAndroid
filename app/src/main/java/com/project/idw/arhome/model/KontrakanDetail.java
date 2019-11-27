package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KontrakanDetail {

    public static final String TAG_ID = "id_kontrakan";

    @SerializedName("id_kontrakan")
    @Expose
    private String idKontrakan;
    @SerializedName("deskripsi_kontrakan")
    @Expose
    private String deskripsiKontrakan;
    @SerializedName("alamat_kontrakan")
    @Expose
    private String alamatKontrakan;
    @SerializedName("fasilitas_kontrakan")
    @Expose
    private String fasilitasKontrakan;
    @SerializedName("foto_kontrakan_1")
    @Expose
    private String fotoKontrakan1;
    @SerializedName("foto_kontrakan_2")
    @Expose
    private String fotoKontrakan2;
    @SerializedName("foto_kontrakan_3")
    @Expose
    private String fotoKontrakan3;
    @SerializedName("harga_kontrakan")
    @Expose
    private String hargaKontrakan;
    @SerializedName("altitude")
    @Expose
    private String altitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("nama_kontrakan")
    @Expose
    private String namaKontrakan;
    @SerializedName("rating_kontrakan")
    @Expose
    private String ratingKontrakan;
    @SerializedName("status_kontrakan")
    @Expose
    private String statusKontrakan;
    @SerializedName("waktu_post")
    @Expose
    private String waktuPost;
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
    @SerializedName("alamat_pemilik")
    @Expose
    private String alamatPemilik;

    public String getIdKontrakan() {
        return idKontrakan;
    }

    public void setIdKontrakan(String idKontrakan) {
        this.idKontrakan = idKontrakan;
    }

    public String getDeskripsiKontrakan() {
        return deskripsiKontrakan;
    }

    public void setDeskripsiKontrakan(String deskripsiKontrakan) {
        this.deskripsiKontrakan = deskripsiKontrakan;
    }

    public String getAlamatKontrakan() {
        return alamatKontrakan;
    }

    public void setAlamatKontrakan(String alamatKontrakan) {
        this.alamatKontrakan = alamatKontrakan;
    }

    public String getFasilitasKontrakan() {
        return fasilitasKontrakan;
    }

    public void setFasilitasKontrakan(String fasilitasKontrakan) {
        this.fasilitasKontrakan = fasilitasKontrakan;
    }

    public String getFotoKontrakan1() {
        return fotoKontrakan1;
    }

    public void setFotoKontrakan1(String fotoKontrakan1) {
        this.fotoKontrakan1 = fotoKontrakan1;
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

    public String getHargaKontrakan() {
        return hargaKontrakan;
    }

    public void setHargaKontrakan(String hargaKontrakan) {
        this.hargaKontrakan = hargaKontrakan;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
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

    public String getNamaKontrakan() {
        return namaKontrakan;
    }

    public void setNamaKontrakan(String namaKontrakan) {
        this.namaKontrakan = namaKontrakan;
    }

    public String getRatingKontrakan() {
        return ratingKontrakan;
    }

    public void setRatingKontrakan(String ratingKontrakan) {
        this.ratingKontrakan = ratingKontrakan;
    }

    public String getStatusKontrakan() {
        return statusKontrakan;
    }

    public void setStatusKontrakan(String statusKontrakan) {
        this.statusKontrakan = statusKontrakan;
    }

    public String getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(String waktuPost) {
        this.waktuPost = waktuPost;
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

    public String getAlamatPemilik() {
        return alamatPemilik;
    }

    public void setAlamatPemilik(String alamatPemilik) {
        this.alamatPemilik = alamatPemilik;
    }

}