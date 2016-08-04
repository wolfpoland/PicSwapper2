package com.example.pacio_000.picswapper2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by pacio_000 on 21.06.2016.
 */
public class Polaczenie3 extends AsyncTask<Void, Void, Uzytkownik> {
    private String adres;
    private Uzytkownik uz;
    private Context cont;
    private Kolejny kolejny;
    private boolean dzialanie;
    private String sciezka;
    public Polaczenie3(String adres, Uzytkownik uz, Context cont) {
        this.adres = adres;
        this.uz = uz;
        this.cont = cont;
        dzialanie=false;
    }

    @Override
    protected Uzytkownik doInBackground(Void... params) {
//        kolejny=new Kolejny();
        Log.d("dupa", "Adres w onClick: " + adres);
        //uz=Kolejny.getUzytkownik();
        try {
            Socket sock = new Socket("10.0.1.149", 8888);
            Log.d("dupa", "Poszlo");
            InputStream in = sock.getInputStream();
            Log.d("dupa", "in poszedl");
            OutputStream out = sock.getOutputStream();
            Log.d("dupa", "out poszedl");
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Log.d("dupa", "oos poszedl");
            ;
            ObjectInputStream ois = new ObjectInputStream(in);
            Log.d("dupa", "ois poszedl");

            Log.d("dupa", "Wysyłam wiadomosc powitalna");
            out.write(4);
            out.flush();
            int zap = in.read();
            Log.d("dupa", "Zapytanie od serwera: " + zap);
            Log.d("dupa", "Wysylam id do serwera: " + uz.getID());
            out.write(uz.getID());
            out.flush();
            File f = new File(adres);
            oos.writeObject(f);

            System.out.println("Przed odbieraniem pliku od serwera");
            PlikInfo plik = (PlikInfo) ois.readObject();
            System.out.println("Plik odebrano ");

            if (plik.isPlikor()) {
                // String combo=PanelGlowny.getjComboBox1().getSelectedItem().toString();
                File plik2 = new File(cont.getFilesDir(), plik.getNazwa());
               // File plik2=new File(Environment.getExternalStorageDirectory().getAbsoluteFile(),plik.getNazwa());
                System.out.println("Sciezka: " + plik2.getAbsolutePath());
                if (!plik2.exists()) {
                    System.out.println("Plik nie istnieje wiec go tworze");
                 plik2.createNewFile();
                } else if (plik2.exists()) {
                    System.out.println("Plik istnieje");
                }
               FileOutputStream fos = new FileOutputStream(plik2);
               // FileOutputStream fos=cont.openFileOutput(plik.getNazwa(),Context.MODE_PRIVATE);
              //  BufferedOutputStream bos = new BufferedOutputStream(fos);
               // Writer outer=new OutputStreamWriter(fos);
                fos.write(plik.getTablica());
              //  bos.close();
                fos.close();
                sciezka=plik2.getAbsolutePath();
                System.out.println("Koniec polaczenia3");
            }

            ois.close();
            oos.close();
            out.close();
            in.close();
            sock.close();



        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (OptionalDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        kolejny.dodajeDoPanelu();
        dzialanie=true;
        return null;
    }
    public boolean getDzialanie(){
        return dzialanie;
    }
    public String getSciezka(){
        return sciezka;
    }

}