package com.info.privateroom;

public class Mesajlar {
    private String mesaj_key;
    private String mesaj_atan;
    private String mesaj;
    private int mesaj_id;

    public Mesajlar() {
    }

    public Mesajlar(String mesaj_key, String mesaj_atan, String mesaj, int mesaj_id) {
        this.mesaj_key = mesaj_key;
        this.mesaj_atan = mesaj_atan;
        this.mesaj = mesaj;
        this.mesaj_id = mesaj_id;
    }

    public int getMesaj_id() {
        return mesaj_id;
    }

    public void setMesaj_id(int mesaj_id) {
        this.mesaj_id = mesaj_id;
    }

    public String getMesaj_key() {
        return mesaj_key;
    }

    public void setMesaj_key(String mesaj_key) {
        this.mesaj_key = mesaj_key;
    }

    public String getMesaj_atan() {
        return mesaj_atan;
    }

    public void setMesaj_atan(String mesaj_atan) {
        this.mesaj_atan = mesaj_atan;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
