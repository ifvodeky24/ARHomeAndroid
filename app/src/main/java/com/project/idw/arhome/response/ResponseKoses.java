package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.Kos;

public class ResponseKoses {

    @SerializedName("master")
    @Expose
    private List<Kos> master = null;

    public List<Kos> getMaster() {
        return master;
    }

    public void setMaster(List<Kos> master) {
        this.master = master;
    }

}