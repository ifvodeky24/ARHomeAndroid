package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingKos {

    @SerializedName("id_pengguna")
    @Expose
    private String idPengguna;
    @SerializedName("id_kos")
    @Expose
    private String idKos;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("id_pemesanan_kos")
    @Expose
    private Integer idPemesananKos;

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getIdKos() {
        return idKos;
    }

    public void setIdKos(String idKos) {
        this.idKos = idKos;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getIdPemesananKos() {
        return idPemesananKos;
    }

    public void setIdPemesananKos(Integer idPemesananKos) {
        this.idPemesananKos = idPemesananKos;
    }

}
