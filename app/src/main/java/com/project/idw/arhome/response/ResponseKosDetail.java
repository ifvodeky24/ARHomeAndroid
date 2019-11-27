package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.KosDetail;

public class ResponseKosDetail {

    @SerializedName("master")
    @Expose
    private List<KosDetail> master = null;

    public List<KosDetail> getMaster() {
        return master;
    }

    public void setMaster(List<KosDetail> master) {
        this.master = master;
    }

}