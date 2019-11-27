package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.Kontrakan;

public class ResponseKontrakans {

    @SerializedName("master")
    @Expose
    private List<Kontrakan> master = null;

    public List<Kontrakan> getMaster() {
        return master;
    }

    public void setMaster(List<Kontrakan> master) {
        this.master = master;
    }

}
