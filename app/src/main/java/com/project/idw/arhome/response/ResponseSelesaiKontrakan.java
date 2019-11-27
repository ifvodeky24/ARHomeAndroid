package com.project.idw.arhome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.SelesaiKontrakan;

public class ResponseSelesaiKontrakan {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SelesaiKontrakan data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SelesaiKontrakan getData() {
        return data;
    }

    public void setData(SelesaiKontrakan data) {
        this.data = data;
    }

}
