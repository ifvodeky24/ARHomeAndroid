package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DalamPemesananKos {

    @SerializedName("id_pemesanan_kos")
    @Expose
    private Integer idPemesananKos;
    @SerializedName("id_kos")
    @Expose
    private Integer idKos;
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Double rating;

    public Integer getIdPemesananKos() {
        return idPemesananKos;
    }

    public void setIdPemesananKos(Integer idPemesananKos) {
        this.idPemesananKos = idPemesananKos;
    }

    public Integer getIdKos() {
        return idKos;
    }

    public void setIdKos(Integer idKos) {
        this.idKos = idKos;
    }

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
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

}
