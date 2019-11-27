package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.RekomendasiKontrakans;

public class ResponseRekomendasiKontrakans {

    @SerializedName("master")
    @Expose
    private List<RekomendasiKontrakans> master = null;

    public List<RekomendasiKontrakans> getMaster() {
        return master;
    }

    public void setMaster(List<RekomendasiKontrakans> master) {
        this.master = master;
    }

}