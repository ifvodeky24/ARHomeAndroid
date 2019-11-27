package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PemesananKoses;

public class ResponsePemesananKoses {

    @SerializedName("master")
    @Expose
    private List<PemesananKoses> master = null;

    public List<PemesananKoses> getMaster() {
        return master;
    }

    public void setMaster(List<PemesananKoses> master) {
        this.master = master;
    }

}