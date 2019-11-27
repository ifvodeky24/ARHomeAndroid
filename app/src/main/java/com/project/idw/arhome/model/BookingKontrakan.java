package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingKontrakan {

    @SerializedName("id_pengguna")
    @Expose
    private String idPengguna;
    @SerializedName("id_kontrakan")
    @Expose
    private String idKontrakan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("id_pemesanan_kontrakan")
    @Expose
    private Integer idPemesananKontrakan;

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getIdKontrakan() {
        return idKontrakan;
    }

    public void setIdKontrakan(String idKontrakan) {
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

    public Integer getIdPemesananKontrakan() {
        return idPemesananKontrakan;
    }

    public void setIdPemesananKontrakan(Integer idPemesananKontrakan) {
        this.idPemesananKontrakan = idPemesananKontrakan;
    }

}
