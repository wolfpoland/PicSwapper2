package com.example.pacio_000.picswapper2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by pacio_000 on 25.06.2016.
 */
public class BlankFragment extends Fragment {
    private List<Linki> linki;
    public BlankFragment(){
        getEtykiety();
    }
    private void getEtykiety(){
        linki=Linki.lista;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView=inflater.inflate(R.layout.fragment_blank,container,false);
        RecyclerView rv=(RecyclerView) rootView.findViewById(R.id.rv_recycler_view);
        rv.setHasFixedSize(true);
        MyAdapter adapter=new MyAdapter(linki);
        rv.setAdapter(adapter);



        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        return rootView;

    }

}
