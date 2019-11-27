package com.project.idw.arhome.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UncheckRatingKos {

    @SerializedName("id_pemesanan_kos")
    @Expose
    private Integer idPemesananKos;
    @SerializedName("nama_kos")
    @Expose
    private String namaKos;

    public Integer getIdPemesananKos() {
        return idPemesananKos;
    }

    public void setIdPemesananKos(Integer idPemesananKos) {
        this.idPemesananKos = idPemesananKos;
    }

    public String getNamaKos() {
        return namaKos;
    }

    public void setNamaKos(String namaKos) {
        this.namaKos = namaKos;
    }

}
