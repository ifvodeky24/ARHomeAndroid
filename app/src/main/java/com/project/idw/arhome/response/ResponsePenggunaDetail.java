package com.project.idw.arhome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PenggunaDetail;

public class ResponsePenggunaDetail {

    @SerializedName("master")
    @Expose
    private PenggunaDetail master;

    public PenggunaDetail getMaster() {
        return master;
    }

    public void setMaster(PenggunaDetail master) {
        this.master = master;
    }

}
