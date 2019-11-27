package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BatalBookingKontrakan {

    @SerializedName("id_pemesanan_kontrakan")
    @Expose
    private Integer idPemesananKontrakan;
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("id_kontrakan")
    @Expose
    private Integer idKontrakan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Double rating;

    public Integer getIdPemesananKontrakan() {
        return idPemesananKontrakan;
    }

    public void setIdPemesananKontrakan(Integer idPemesananKontrakan) {
        this.idPemesananKontrakan = idPemesananKontrakan;
    }

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Integer getIdKontrakan() {
        return idKontrakan;
    }

    public void setIdKontrakan(Integer idKontrakan) {
        this.idKontrakan = idKontrakan;
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
