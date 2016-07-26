package com.example.pacio_000.picswapper2;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by pacio_000 on 19.06.2016.
 */
public class Adapter extends BaseAdapter {

    private Context context;
    private Dater[] data;
    private static LayoutInflater inflater=null;

    public Adapter(Context context,Dater[] data){
        this.context=context;
        this.data=data;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(vi==null){
            vi=inflater.inflate(R.layout.wiersz, null);
        }
        ImageView iv=(ImageView) vi.findViewById(R.id.imageView);
        TextView text=(TextView) vi.findViewById(R.id.textView3);
        if(!data[position].getSystemowy()) {
            if (data[position].getData().contains(".")) {
                iv.setImageResource(R.mipmap.ic_nfile);
            } else {
                iv.setImageResource(R.mipmap.ic_nfolder);
            }

            text.setText(data[position].getData());
        }else{
            Log.d("dupa ","Systemowy!");
            if(data[position].getData().contains(".")){
                File plik=new File(context.getFilesDir(),data[position].getData());
                if(plik.exists()){
                    Bitmap bit= BitmapFactory.decodeFile(plik.getAbsolutePath());
                    iv.setImageBitmap(bit);
                    text.setTextColor(Color.BLACK);
                    text.setText(data[position].getData());

                }
            }
        }
     /*   convertView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String adres=Kolejny.getListaNaPozycji(position).getAdres();
                Log.d("dupa","Adres w onClick: "+adres);
                try {
                    Socket sock=new Socket("localhost",55);
                    InputStream in =sock.getInputStream();
                    OutputStream out= sock.getOutputStream();
                    ObjectInputStream ois=new ObjectInputStream(in);
                    ObjectOutputStream oos=new ObjectOutputStream(out);
                    Log.d("dupa","Wysyłam wiadomosc powitalna");
                    out.write(3);
                    out.flush();
                    int zap=in.read();
                    Log.d("dupa","Zapytanie od serwera: "+zap);



                    ois.close();
                    oos.close();
                    out.close();
                    in.close();
                    sock.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/


        return vi;
    }
}
