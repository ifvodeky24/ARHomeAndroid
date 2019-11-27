package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PemilikDetail;

public class ResponsePemilikDetail {

    @SerializedName("master")
    @Expose
    private List<PemilikDetail> master = null;

    public List<PemilikDetail> getMaster() {
        return master;
    }

    public void setMaster(List<PemilikDetail> master) {
        this.master = master;
    }

}