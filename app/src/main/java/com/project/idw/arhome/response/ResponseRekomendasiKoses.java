package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.RekomendasiKoses;

public class ResponseRekomendasiKoses {

    @SerializedName("master")
    @Expose
    private List<RekomendasiKoses> master = null;

    public List<RekomendasiKoses> getMaster() {
        return master;
    }

    public void setMaster(List<RekomendasiKoses> master) {
        this.master = master;
    }

}
