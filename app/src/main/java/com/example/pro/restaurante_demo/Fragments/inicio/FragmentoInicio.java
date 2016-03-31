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

import io.realm.Realm;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by pro on 16/02/16.
 */
public class FragmentoInicio extends Fragment{

    private Realm realm;
    private View view;
    private ViewHolder holder = null;
    private AdaptadorInicio adaptador;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_inicio, container, false);

        holder = new ViewHolder(view);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        holder.reciclador.setLayoutManager(layoutManager);

        adaptador = new AdaptadorInicio();

        holder.reciclador.setAdapter(new ScaleInAnimationAdapter(adaptador));

    }

    class ViewHolder{
        public RecyclerView reciclador;

        public ViewHolder(View view){
            reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        }
    }

}
