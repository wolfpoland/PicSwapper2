package com.example.pacio_000.picswapper2;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Kolejny extends AppCompatActivity {
    private Uzytkownik uz;
    private ListView list;
    //private ArrayAdapter<String> lista;
    private Context cont;
    private Polaczenie2 pol;
    private static List<Linki> lista=null;
    private boolean mozna=false;
    private boolean dzialanie=false;
    private CollapsingToolbarLayout col;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cont=getApplicationContext();
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_kolejny);


        setContentView(R.layout.activity_kolejny);
      //  ActionBar bar = getActionBar();
       // bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#d3d3d3")));
       // TextView text=(TextView) findViewById(R.id.tv_text);
      //  Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Advent_Pro/AdventPro-Medium.ttf");
        //text.setTypeface(face);
        //text.setTextColor(Color.BLACK);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.GRAY);
        setSupportActionBar(toolbar);
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewpager);
        PagerAdapter pagerAdapter =new PagerAdapter(getSupportFragmentManager(),Kolejny.this);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(Color.GRAY);
        tabLayout.setupWithViewPager(viewPager);
        for(int n=0;n<tabLayout.getTabCount();n++){
            TabLayout.Tab tab=tabLayout.getTabAt(n);

            tab.setCustomView(pagerAdapter.getTabView(n));
        }
       // col=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
       // col.setTitle("Papaparapapa");
        //ImageView view =(ImageView) findViewById(R.id.header);
        //Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        //Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {


       // list=(ListView) findViewById(R.id.listView);
        Intent i =getIntent();
        final Uzytkownik uz= (Uzytkownik) i.getSerializableExtra("papa");
        int id=uz.getID();
        Log.d("dupa","W kolejny: "+Integer.toString(id));
        pol =new Polaczenie2(id);
        pol.execute();
        System.out.println("Polaczono2");
        View lol=findViewById(R.id.card_view);
      /*  lol.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d("dupa","Klik");
            }

        });
        */
        /*
        /*
        list.setNestedScrollingEnabled(false);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String adres = Kolejny.getListaNaPozycji(position).getAdres();

                Polaczenie3 pol = new Polaczenie3(adres, uz, getApplicationContext());
                pol.execute();
                Log.d("dupa", "przed dodaje do panelu");
                while(dzialanie==false) {
                    dzialanie=pol.getDzialanie();
                    if(dzialanie==true) {
                        Log.d("dzialanie", "Poszedl if");
                        Dater[] tabka=dodajeDoPanelu();
                        list.setAdapter(new Adapter(cont, tabka));
                    }
                }
            }
        });
        try {
            while (mozna == false) {
                mozna = pol.getMozna();
                if (mozna) {
                    lista = pol.getLinki();
                    Log.d("dupa", "Lista nie jest nullem pracuje");
                    ArrayList<Dater> nazwy = new ArrayList<Dater>();

                    for (Linki l : lista) {
                        File plik = new File(l.getAdres());
                        String sciezka=plik.getName().substring(plik.getName().lastIndexOf("\\")+1);

                        Log.d("dupa","sciezka: "+File.separator);
                        nazwy.add(new Dater(sciezka,false));
                        Log.d("dupa", "nazwa: " + sciezka.substring(sciezka.lastIndexOf(File.separator)+1));
                    }
                    File plik=new File(getApplicationContext().getFilesDir().getAbsolutePath());
                    List<Dater> pliki=new ArrayList<Dater>();
                    for(String s : plik.list()){
                        pliki.add(new Dater(s,true));
                    }
                    for(Dater s: pliki){
                        nazwy.add(s);
                    }
                    //  ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.wiersz,nazwy);
                    Dater[] tablica = new Dater[nazwy.size()];
                    int p = 0;
                    for (Dater s : nazwy) {
                       // String tmp = s;
                        tablica[p]=s;
                        p++;
                    }
                    list.setAdapter(new Adapter(this, tablica));

                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
*/
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kolejny, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Log.d("dupa","Klik"+id);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public  Uzytkownik getUzytkownik(){return uz;}
    public List<Linki> getLista(){
       return lista;
    }
    public static Linki getListaNaPozycji(int pozycja){
        return lista.get(pozycja);
    }

    public  Dater[] dodajeDoPanelu(){
        Log.d("dupa","W dodaje do apnelu");
        File plik=new File(getApplicationContext().getFilesDir().getAbsolutePath());
        List<String> pliki=new ArrayList<String>();
        for(String s : plik.list()){
            pliki.add(s);
        }
        Dater[] tablica=new Dater[pliki.size()];
        int n=0;
        for(String s : pliki){
            System.out.println("Wypisuje: "+s);
            String data=s;
            tablica[n]=new Dater(data,true);
            Log.d("dupa","Wypisuje: "+s);
            n++;
        }
        return tablica;
    }
    class PagerAdapter extends FragmentPagerAdapter{
        String[] tytuly=new String[] {"Pierwsza","Druga","Trzecia"};
        Context context;

        public PagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context=context;
        }

        @Override
        public Fragment getItem(int position) {
            return new BlankFragment();
        }

        @Override
        public int getCount() {
            return tytuly.length;
        }
        @Override
        public CharSequence getPageTitle(int position){
            return tytuly[position];
        }
        public View getTabView(int position){
            View tab= LayoutInflater.from(Kolejny.this).inflate(R.layout.custom_tab,null);
            TextView tv=(TextView) tab.findViewById(R.id.custom_text);
            tv.setText(tytuly[position]);
            return tab;
        }
    }
}
