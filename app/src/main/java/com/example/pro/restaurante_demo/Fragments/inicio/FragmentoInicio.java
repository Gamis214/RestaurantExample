package com.example.pro.restaurante_demo.Fragments.inicio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.restaurante_demo.Adapters.AdaptadorInicio;
import com.example.pro.restaurante_demo.R;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by pro on 16/02/16.
 */
public class FragmentoInicio extends Fragment{

    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        adaptador = new AdaptadorInicio();

        reciclador.setAdapter(new ScaleInAnimationAdapter(adaptador));
        return view;

    }
}
