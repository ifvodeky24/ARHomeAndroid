package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TambahKos {

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
    @SerializedName("id_pemilik")
    @Expose
    private String idPemilik;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("altitude")
    @Expose
    private String altitude;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("stok_kamar")
    @Expose
    private String stokKamar;
    @SerializedName("jenis_kos")
    @Expose
    private String jenisKos;
    @SerializedName("id_kos")
    @Expose
    private Integer idKos;

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

    public String getIdPemilik() {
        return idPemilik;
    }

    public void setIdPemilik(String idPemilik) {
        this.idPemilik = idPemilik;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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

    public String getStokKamar() {
        return stokKamar;
    }

    public void setStokKamar(String stokKamar) {
        this.stokKamar = stokKamar;
    }

    public String getJenisKos() {
        return jenisKos;
    }

    public void setJenisKos(String jenisKos) {
        this.jenisKos = jenisKos;
    }

    public Integer getIdKos() {
        return idKos;
    }

    public void setIdKos(Integer idKos) {
        this.idKos = idKos;
    }

}