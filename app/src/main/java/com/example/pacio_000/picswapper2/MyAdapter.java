package com.example.pacio_000.picswapper2;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by pacio_000 on 25.06.2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static List<Linki> mDataSet;
    public  static CardView card;
   // public static View itemView;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public CardView mCardView;
        public TextView mTextView;
        public ImageView mImageView;
       public TextView mTextView2;


       // public CardView card;
        public int wysokosc;

        public MyViewHolder(final View itemView) {
            super(itemView);
           // this.itemView=itemView;
            mCardView=(CardView) itemView.findViewById(R.id.card_view);
            mTextView=(TextView) itemView.findViewById(R.id.tv_text);
            mImageView=(ImageView) itemView.findViewById(R.id.iv_image);
             card=(CardView) itemView.findViewById(R.id.card_view);
            mTextView2=(TextView) itemView.findViewById(R.id.tv_blah);
            itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
                @Override
               public boolean onPreDraw(){
                    Log.d("cos","Cos dziala");
                   itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                    wysokosc=card.getHeight();
                    ViewGroup.LayoutParams layoutParams=itemView.getLayoutParams();
                    layoutParams.height=170;
                    itemView.setLayoutParams(layoutParams);
                    return true;
               }
            });

            itemView.findViewById(R.id.iv_image);
            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    int pozycja=v.getVerticalScrollbarPosition();
                    Log.d("dupaP", String.valueOf(v.getVerticalScrollbarPosition()));
                    toggleProductDescriptionHeight(itemView);
                    Log.d("dupaP", mDataSet.get(pozycja).getAdres());
                    Log.d("dupaP", mDataSet.get(pozycja).getUzytkownik().getImie());
                    Log.d("dupaP","Klik");

                    Polaczenie3 polo=new Polaczenie3(mDataSet.get(pozycja).getAdres(),mDataSet.get(pozycja).getUzytkownik(),Etykiety.cont);
                    polo.execute();
                    System.out.println("Przed while");
                    do{
                        System.out.println("Krece czekajac na obrazek");
                        if(polo.getDzialanie()==true){
                            File obraz=new File(polo.getSciezka());
                           // if(obraz.exists()){
                                mTextView2.setText("");
                                System.out.println("Pokazuje w adapterze");
                                Log.d("widze","Obrazo");
                                Bitmap bitmap= BitmapFactory.decodeFile(obraz.getAbsolutePath());
                                mImageView.setImageBitmap(bitmap);
                                //CardView card=(CardView) itemView.findViewById(R.id.card_view);

                                card.setMinimumHeight(100);
                                card.setCardBackgroundColor(Color.BLUE);
                                card.invalidate();
                                itemView.invalidate();

                        //    }
                        }
                    } while(polo.getDzialanie()==false);
                }

            });
        }
    }
    public  static void toggleProductDescriptionHeight(final View itemView){
        Log.d("cos","Cos funkcja tez rusza");
        int descriptionViewMinHeight=62;
        if(descriptionViewMinHeight == 62){
            Log.d("cos","zaszedl if");
            ValueAnimator anim= ValueAnimator.ofInt(itemView.getMeasuredHeightAndState(),200);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
               public void onAnimationUpdate(ValueAnimator valueAnimator){
                   int val=(Integer) valueAnimator.getAnimatedValue();
                   ViewGroup.LayoutParams layoutParams=itemView.getLayoutParams();
                   layoutParams.height=val;
                   itemView.setLayoutParams(layoutParams);
                   //itemView.setCardBackgroundColor(Color.BLUE);
               }
            });
            anim.start();
        }else{
            Log.d("cos","zaszedl else");
            ValueAnimator anim= ValueAnimator.ofInt(card.getMeasuredHeightAndState(),card.getHeight());
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
                public void onAnimationUpdate(ValueAnimator valueAnimator){
                    int val=(Integer) valueAnimator.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams=card.getLayoutParams();
                    layoutParams.height=val;
                    card.setLayoutParams(layoutParams);
                }
            });
            anim.start();
        }
    }
    public MyAdapter(List<Linki> mDataSet){
        Log.d("dupa","Adapter poszedl !");
        this.mDataSet=mDataSet;
    }
    public MyAdapter(){}

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        MyViewHolder mvh=new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mCardView.setTag(position);
        holder.mTextView.setText(mDataSet.get(position).getAdres());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    public void setmDataSet(String[] dane){

    }
}
