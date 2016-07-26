package com.example.pacio_000.picswapper2;

/**
 * Created by pacio_000 on 23.06.2016.
 */
public class Dater {
    private String data;
    private boolean systemowy;
    public Dater(String data, boolean systemowy){
        this.data=data;
        this.systemowy=systemowy;
    }
    public String getData(){
        return data;
    }
    public boolean getSystemowy(){
        return systemowy;
    }
}
