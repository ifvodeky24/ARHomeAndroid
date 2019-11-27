package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PemesananKosDetail;

public class ResponsePemesananKosDetail {

    @SerializedName("master")
    @Expose
    private List<PemesananKosDetail> master = null;

    public List<PemesananKosDetail> getMaster() {
        return master;
    }

    public void setMaster(List<PemesananKosDetail> master) {
        this.master = master;
    }

}