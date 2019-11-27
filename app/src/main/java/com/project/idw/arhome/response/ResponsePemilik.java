package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.Pemilik;

public class ResponsePemilik {

    @SerializedName("master")
    @Expose
    private List<Pemilik> master = null;

    public List<Pemilik> getMaster() {
        return master;
    }

    public void setMaster(List<Pemilik> master) {
        this.master = master;
    }

}