package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KosDetail {

    public static final String TAG_ID = "id_kos";

    @SerializedName("id_kos")
    @Expose
    private String idKos;
    @SerializedName("deskripsi_kos")
    @Expose
    private String deskripsiKos;
    @SerializedName("alamat_kos")
    @Expose
    private String alamatKos;
    @SerializedName("fasilitas_kos")
    @Expose
    private String fasilitasKos;
    @SerializedName("foto_kos_1")
    @Expose
    private String fotoKos1;
    @SerializedName("foto_kos_2")
    @Expose
    private String fotoKos2;
    @SerializedName("foto_kos_3")
    @Expose
    private String fotoKos3;
    @SerializedName("harga_kos")
    @Expose
    private String hargaKos;
    @SerializedName("altitude")
    @Expose
    private String altitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("nama_kos")
    @Expose
    private String namaKos;
    @SerializedName("rating_kos")
    @Expose
    private String ratingKos;
    @SerializedName("status_kos")
    @Expose
    private String statusKos;
    @SerializedName("waktu_post")
    @Expose
    private String waktuPost;
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

    public String getDeskripsiKos() {
        return deskripsiKos;
    }

    public void setDeskripsiKos(String deskripsiKos) {
        this.deskripsiKos = deskripsiKos;
    }

    public String getAlamatKos() {
        return alamatKos;
    }

    public void setAlamatKos(String alamatKos) {
        this.alamatKos = alamatKos;
    }

    public String getFasilitasKos() {
        return fasilitasKos;
    }

    public void setFasilitasKos(String fasilitasKos) {
        this.fasilitasKos = fasilitasKos;
    }

    public String getFotoKos1() {
        return fotoKos1;
    }

    public void setFotoKos1(String fotoKos1) {
        this.fotoKos1 = fotoKos1;
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

    public String getHargaKos() {
        return hargaKos;
    }

    public void setHargaKos(String hargaKos) {
        this.hargaKos = hargaKos;
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

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

    public String getRatingKos() {
        return ratingKos;
    }

    public void setRatingKos(String ratingKos) {
        this.ratingKos = ratingKos;
    }

    public String getStatusKos() {
        return statusKos;
    }

    public void setStatusKos(String statusKos) {
        this.statusKos = statusKos;
    }

    public String getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(String waktuPost) {
        this.waktuPost = waktuPost;
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