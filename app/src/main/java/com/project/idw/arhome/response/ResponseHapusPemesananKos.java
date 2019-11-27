package com.project.idw.arhome.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.idw.arhome.model.HapusPemesananKos;

public class ResponseHapusPemesananKos {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private HapusPemesananKos data;

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

    public HapusPemesananKos getData() {
        return data;
    }

    public void setData(HapusPemesananKos data) {
        this.data = data;
    }

}
