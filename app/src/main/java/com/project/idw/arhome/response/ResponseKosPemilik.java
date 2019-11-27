package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.KosPemilik;

public class ResponseKosPemilik {

    @SerializedName("master")
    @Expose
    private List<KosPemilik> master = null;

    public List<KosPemilik> getMaster() {
        return master;
    }

    public void setMaster(List<KosPemilik> master) {
        this.master = master;
    }

}