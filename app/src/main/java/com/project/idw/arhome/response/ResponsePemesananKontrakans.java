package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.PemesananKontrakans;

public class ResponsePemesananKontrakans {

    @SerializedName("master")
    @Expose
    private List<PemesananKontrakans> master = null;

    public List<PemesananKontrakans> getMaster() {
        return master;
    }

    public void setMaster(List<PemesananKontrakans> master) {
        this.master = master;
    }

}
