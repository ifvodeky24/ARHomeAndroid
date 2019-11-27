package com.project.idw.arhome.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.Pengguna;

public class ResponsePengguna {

    @SerializedName("master")
    @Expose
    private List<Pengguna> master = null;

    public List<Pengguna> getMaster() {
        return master;
    }

    public void setMaster(List<Pengguna> master) {
        this.master = master;
    }

}
