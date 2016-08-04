package com.example.pacio_000.picswapper2;

import java.util.List;

public class Linki {
    public static List<Linki> lista;
    private String adres;
    private Uzytkownik uz;
    public Linki(String adres,Uzytkownik uz){
        this.adres=adres;
        this.uz=uz;
    }
    public Linki(){ }
    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public Uzytkownik getUzytkownik(){
        return uz;
    }

}
