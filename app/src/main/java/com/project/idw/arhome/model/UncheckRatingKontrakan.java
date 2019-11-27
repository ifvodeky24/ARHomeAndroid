package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UncheckRatingKontrakan {

    @SerializedName("id_pemesanan_kontrakan")
    @Expose
    private Integer idPemesananKontrakan;
    @SerializedName("nama_kontrakan")
    @Expose
    private String namaKontrakan;

    public Integer getIdPemesananKontrakan() {
        return idPemesananKontrakan;
    }

    public void setIdPemesananKontrakan(Integer idPemesananKontrakan) {
        this.idPemesananKontrakan = idPemesananKontrakan;
    }

    public String getNamaKontrakan() {
        return namaKontrakan;
    }

    public void setNamaKontrakan(String namaKontrakan) {
        this.namaKontrakan = namaKontrakan;
    }

}
