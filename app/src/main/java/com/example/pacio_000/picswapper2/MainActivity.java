package com.example.pacio_000.picswapper2;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText login;
    EditText haslo;
    Polaczenie pol;
    private Uzytkownik uz=null;
    private VideoView vido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();
       // bar.hide();
        login=(EditText)findViewById(R.id.editText);
        haslo=(EditText)findViewById(R.id.editText);
        vido=(VideoView) findViewById(R.id.videoView);
        ImageView im2=(ImageView) findViewById(R.id.imageView2);
        TextView text=(TextView) findViewById(R.id.textView4);
        text.setText("PicSwapper");
        text.setTextSize(35);
      //  Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Advent_Pro/AdventPro-Medium.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Fugaz_One/FugazOne-Regular.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Graduate/Graduate-Regular.ttf");
    //   Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Jura/Jura-Medium.ttf");
      //  Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Kelly_Slab/KellySlab-Regular.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Leckerli_One/LeckerliOne-Regular.ttf");
   //     Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Lobster/Lobster-Regular.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Marvel/Marvel-Regular.ttf");
     Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Monoton/Monoton-Regular.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Oleo_Script_Swash_Caps/OleoScriptSwashCaps-Regular.ttf");
      //  Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Press_Start_2P/PressStart2P-Regular.ttf");
       // Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Racing_Sans_One/RacingSansOne-Regular.ttf");
      //  Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Six_Caps/SixCaps.ttf");
        text.setTypeface(face);
        text.setTextColor(Color.BLACK);
        im2.setImageResource(R.mipmap.ic_launcher);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        vido.setVideoURI(uri);
        vido.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0f,0f);
            }
        });
        vido.start();

        Button logowanie=(Button)findViewById(R.id.button);
        logowanie.setBackgroundColor(Color.BLACK);
        logowanie.setTextColor(Color.WHITE);
        logowanie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                pol =new Polaczenie(login.getText().toString(),haslo.getText().toString());

                pol.execute();

                System.out.println("Polaczono");
                while(uz==null) {
                    uz = pol.getUzytkownik();
                    if(uz!=null) {
                        System.out.println("Poszlo");
                        Intent i = new Intent(getApplicationContext(), Kolejny.class);
                        i.putExtra("papa",uz);
                        startActivity(i);
                    }
                }

            }
        });
    }

    public  void polacz() throws IOException {
/*


        try {
            Class.forName("org.postgresql.Driver");
            c= DriverManager.getConnection("jdbc:postgresql://localhost:5432/test",
                    "postgres", "haslo");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
     //   klient=new DefaultHttpClient();
        String url="http://localhost:8080/myapp/?login="+login.getText().toString()+"&haslo="+haslo.getText().toString();
        Log.d("dupa",url);
        ArrayList<String> lista=new ArrayList<String>();
        URL lacz=new URL(url);
        URLConnection pol=lacz.openConnection();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(pol.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    JSONArray ja = new JSONArray(line);
                    for (int n = 0; n < ja.length(); n++) {
                        JSONObject jo = (JSONObject) ja.get(n);
                        lista.add(jo.getString("id"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }catch(Exception e){
            e.printStackTrace();
          Log.e("dupa2",e.getMessage());
        }
        for(String s: lista){
            Log.d("dupa3",s);
        }
        /*
        try {

            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

            RestTemplate resto = new RestTemplate();
            resto.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            //ResponseEntity<Uzytkownik> responseEntity = resto.exchange(url, HttpMethod.GET, requestEntity, Uzytkownik.class);
            //Uzytkownik uz = responseEntity.getBody();

            Uzytkownik uz = resto.getForObject(url, Uzytkownik.class);
            login.setText((CharSequence) uz.getImie());
            Log.d("dupa1","Uzytkownik imie: " + uz.getImie());
        }catch(Exception e){
            Log.e("dupa2", e.getMessage(), e);
        }
        */
   /*     metodaGet=new HttpGet(url);
        HttpResponse odp=klient.execute(metodaGet);
        InputStreamReader input=new InputStreamReader(odp.getEntity().getContent());
        BufferedReader in =new  BufferedReader(input);
        StringBuffer sb=new StringBuffer("");
        String line="";
        String separator=System.getProperty("line.separator");*/





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
