package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.LacakKontrakan;

public class ResponseLacakKontrakan {

    @SerializedName("master")
    @Expose
    private List<LacakKontrakan> master = null;

    public List<LacakKontrakan> getMaster() {
        return master;
    }

    public void setMaster(List<LacakKontrakan> master) {
        this.master = master;
    }

}
