package com.example.pacio_000.picswapper2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacio_000 on 16.06.2016.
 */
public class Polaczenie2 extends AsyncTask<Void, Void, Uzytkownik> {
    private String login;
    private String haslo;
    private Uzytkownik uz;
    private ArrayList<Linki> lista=null;
    private int wybor;
    private int id1;
    private boolean mozna;

    public Polaczenie2(String login, String haslo){
        this.login=login;
        this.haslo=haslo;
        wybor=1;
    }
    public Polaczenie2(int id1, Uzytkownik uz){
        this.id1=id1;
        wybor=2;
        mozna=false;
        this.uz=uz;
        Log.d("dupa",Integer.toString(wybor));
    }
    @Override
    protected Uzytkownik doInBackground(Void... params) {
        Log.d("dupa","Z metody kolejny w polaczenie 2");
        switch (wybor) {

            case 1: {
                String url = "http://192.168.1.104:8080/myapp/?login=" + login + "&haslo=" + haslo;
                Log.d("dupa", url);
                ArrayList<Linki> lista = new ArrayList<Linki>();
                try {
                    URL lacz = new URL(url);
                    URLConnection pol = lacz.openConnection();
                    InputStream inner = pol.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(inner));
                    String line;
                    while ((line = in.readLine()) != null) {
                        try {
                            JSONObject ob = new JSONObject(line);
                            // JSONArray ja = ob.getJSONArray()
                            // for (int n = 0; n < ja.length(); n++) {
                            //   JSONObject jo = (JSONObject) ja.get(n);
                           // lista.add (new Linki(ob.getString("adres")));
                            //Uzytkownik test=new Uzytkownik();
                            uz = new Uzytkownik(Integer.parseInt(ob.getString("id")), ob.getString("imie"), ob.getString("nazwisko"), ob.getString("mail"), ob.getString("haslo"));
                            // }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    in.close();
                    inner.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("dupa2", e.getMessage());
                }
                for (Linki s : lista) {
                    Log.d("dupa3", s.getAdres());
                }

                break;
            }
            case 2: {
                Log.d("dupa","W case2 w polaczenie2");
                try {
                    String url = "http://10.0.1.149:8080/Uzytkowniks/pliki?id=" + id1;
                    Log.d("dupa5","kolejne: "+url);
                    lista = new ArrayList<Linki>();
                    URL lacz = new URL(url);
                    URLConnection pol = lacz.openConnection();
                    InputStream inner = pol.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(inner));
                    String line;
                    while ((line = in.readLine()) != null) {
                        try {
                            // JSONObject ob = new JSONObject(line);
                            JSONArray ja = new JSONArray(line);
                            Log.d("dupa","JSONARRAY LENGHT: "+ja.length());
                            for (int n = 0; n < ja.length(); n++) {
                                Log.d("dupa ", "Przed obiektem ale w petli");
                                JSONObject jo = (JSONObject) ja.getJSONObject(n);
                               // jo.get()
                                Log.d("dupa ", "krece: "+n);
                                Log.d("dupa","Wyswietlam napis: "+jo.get("adres"));
                                //Log.d("dupa","Wyswietlam napis: "+jo.getString("adres"));
                                lista.add (new Linki( jo.getString("adres"),uz));
                                //Uzytkownik test=new Uzytkownik();
                                //   uz = new Uzytkownik(Integer.parseInt(ob.getString("id")), ob.getString("imie"), ob.getString("nazwisko"), ob.getString("mail"), ob.getString("haslo"));
                            }
                            if(lista.size()==0){
                                Log.d("dupa","dalej nie dziala");
                            }
                            for (Linki s : lista) {
                                System.out.println(s.getAdres());
                                Log.d("dupa3",s.getAdres());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    mozna=true;
                    in.close();
                    inner.close();
                } catch (Exception e) {

                }

                break;


            }

        }
        return null;
    }
    public Uzytkownik getUzytkownik(){
        return uz;
    }
    public List<Linki> getLinki(){return lista; }
    public boolean getMozna(){return mozna;}



}