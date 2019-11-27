package com.project.idw.arhome.model;

public class Chatlist {
    public String id;
    public String id_now;

    public String getId_now() {
        return id_now;
    }

    public void setId_now(String id_now) {
        this.id_now = id_now;
    }

    public Chatlist(String id, String id_now) {
        this.id = id;
        this.id_now = id_now;

    }

    public Chatlist() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
