package com.example.pro.restaurante_demo.Fragments.Categorias;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.restaurante_demo.Adapters.AdaptadorCategorias;
import com.example.pro.restaurante_demo.Models.Comida;
import com.example.pro.restaurante_demo.R;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * Created by pro on 16/02/16.
 */
public class FragmentoCategoria extends Fragment {

    private static final String INDICE_SECCION = "";

    private RecyclerView reciclador;
    private GridLayoutManager gridlayoutManager;
    private AdaptadorCategorias adaptador;

    public static FragmentoCategoria nuevaInstancia(int indiceSeccion) {
        FragmentoCategoria fragment = new FragmentoCategoria();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        gridlayoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(gridlayoutManager);

        int indiceSeccion = getArguments().getInt(INDICE_SECCION);

        switch (indiceSeccion) {
            case 0:
                //adaptador = new AdaptadorCategorias(Comida.PLATILLOS);
                break;
            case 1:
                //adaptador = new AdaptadorCategorias(Comida.BEBIDAS);
                break;
            case 2:
                //adaptador = new AdaptadorCategorias(Comida.POSTRES);
                break;
        }

        reciclador.setAdapter( new ScaleInAnimationAdapter(adaptador));

        return view;
    }

}
