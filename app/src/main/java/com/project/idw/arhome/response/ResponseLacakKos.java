package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.LacakKos;

public class ResponseLacakKos {

    @SerializedName("master")
    @Expose
    private List<LacakKos> master = null;

    public List<LacakKos> getMaster() {
        return master;
    }

    public void setMaster(List<LacakKos> master) {
        this.master = master;
    }

}