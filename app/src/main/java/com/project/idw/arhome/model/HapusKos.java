package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HapusKos {

    @SerializedName("id_kos")
    @Expose
    private Integer idKos;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("foto_2")
    @Expose
    private String foto2;
    @SerializedName("foto_3")
    @Expose
    private String foto3;
    @SerializedName("waktu_post")
    @Expose
    private String waktuPost;
    @SerializedName("id_pemilik")
    @Expose
    private Integer idPemilik;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("altitude")
    @Expose
    private Integer altitude;
    @SerializedName("harga")
    @Expose
    private Integer harga;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("stok_kamar")
    @Expose
    private Integer stokKamar;
    @SerializedName("jenis_kos")
    @Expose
    private String jenisKos;

    public Integer getIdKos() {
        return idKos;
    }

    public void setIdKos(Integer idKos) {
        this.idKos = idKos;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFoto2() {
        return foto2;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public String getFoto3() {
        return foto3;
    }

    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    public String getWaktuPost() {
        return waktuPost;
    }

    public void setWaktuPost(String waktuPost) {
        this.waktuPost = waktuPost;
    }

    public Integer getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(Integer idPemilik) {
        this.idPemilik = idPemilik;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getStokKamar() {
        return stokKamar;
    }

    public void setStokKamar(Integer stokKamar) {
        this.stokKamar = stokKamar;
    }

    public String getJenisKos() {
        return jenisKos;
    }

    public void setJenisKos(String jenisKos) {
        this.jenisKos = jenisKos;
    }

}
