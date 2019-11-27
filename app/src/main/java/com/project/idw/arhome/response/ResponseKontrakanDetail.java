package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.KontrakanDetail;

public class ResponseKontrakanDetail {

    @SerializedName("master")
    @Expose
    private List<KontrakanDetail> master = null;

    public List<KontrakanDetail> getMaster() {
        return master;
    }

    public void setMaster(List<KontrakanDetail> master) {
        this.master = master;
    }

}