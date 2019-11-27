package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PemesananKontrakanDetail;

public class ResponsePemesananKontrakanDetail {

    @SerializedName("master")
    @Expose
    private List<PemesananKontrakanDetail> master = null;

    public List<PemesananKontrakanDetail> getMaster() {
        return master;
    }

    public void setMaster(List<PemesananKontrakanDetail> master) {
        this.master = master;
    }

}