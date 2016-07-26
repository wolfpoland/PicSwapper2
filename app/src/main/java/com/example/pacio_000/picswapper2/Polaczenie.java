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

/**
 * Created by pacio_000 on 16.06.2016.
 */
public class Polaczenie extends AsyncTask<Void, Void, Uzytkownik> {
    private String login;
    private String haslo;
    private Uzytkownik uz;
    private int wybor;
    private int id1;
    public Polaczenie(String login, String haslo){
        this.login=login;
        this.haslo=haslo;
        wybor=1;
    }
    public Polaczenie(int id1){
        this.id1=id1;
        wybor=2;
    }
    @Override
    protected Uzytkownik doInBackground(Void... params) {
        switch (wybor) {
            case 1: {
                String url = "http://192.168.1.106:8080/myapp/?login=" + login + "&haslo=" + haslo;
                Log.d("dupa", url);
                ArrayList<String> lista = new ArrayList<String>();
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
                            lista.add(ob.getString("id"));
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
                for (String s : lista) {
                    Log.d("dupa3", s);
                }

                break;
            }
            case 2: {
                try {
                    String url = "http://192.168.1.106:8080/myapp/pliki/?id=" + uz.getID();
                    Log.d("dupa5","kolejne: "+url);
                    ArrayList<String> lista = new ArrayList<>();
                    URL lacz = new URL(url);
                    URLConnection pol = lacz.openConnection();
                    InputStream inner = pol.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(inner));
                    String line;
                    while ((line = in.readLine()) != null) {
                        try {
                            // JSONObject ob = new JSONObject(line);
                            JSONArray ja = new JSONArray(line);
                            for (int n = 0; n < ja.length(); n++) {
                                JSONObject jo = (JSONObject) ja.get(n);
                                lista.add(jo.getString(""));
                                //Uzytkownik test=new Uzytkownik();
                                //   uz = new Uzytkownik(Integer.parseInt(ob.getString("id")), ob.getString("imie"), ob.getString("nazwisko"), ob.getString("mail"), ob.getString("haslo"));
                            }
                            for (String s : lista) {
                                System.out.println(s);
                                Log.d("dupa3",s);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
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




}
