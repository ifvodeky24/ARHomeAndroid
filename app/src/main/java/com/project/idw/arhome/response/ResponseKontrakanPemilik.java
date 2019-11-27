package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.KontrakanPemilik;

public class ResponseKontrakanPemilik {

    @SerializedName("master")
    @Expose
    private List<KontrakanPemilik> master = null;

    public List<KontrakanPemilik> getMaster() {
        return master;
    }

    public void setMaster(List<KontrakanPemilik> master) {
        this.master = master;
    }

}