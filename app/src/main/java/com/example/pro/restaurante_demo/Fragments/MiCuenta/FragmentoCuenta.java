package com.example.pro.restaurante_demo.Fragments.MiCuenta;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pro.restaurante_demo.Adapters.AdaptadorSecciones;
import com.example.pro.restaurante_demo.Models.SeccionesMiCuenta;
import com.example.pro.restaurante_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pro on 16/02/16.
 */
public class FragmentoCuenta extends Fragment{

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {

        List<SeccionesMiCuenta> lstFragments = new ArrayList<>();
        SeccionesMiCuenta s1 = new SeccionesMiCuenta(new FragmentoPerfil(),getString(R.string.titulo_tab_perfil));
        SeccionesMiCuenta s2 = new SeccionesMiCuenta(new FragmentoDirecciones(),getString(R.string.titulo_tab_direcciones));
        SeccionesMiCuenta s3 = new SeccionesMiCuenta(new FragmentoTarjetas(),getString(R.string.titulo_tab_tarjetas));
        lstFragments.add(s1);
        lstFragments.add(s2);
        lstFragments.add(s3);

        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager(),lstFragments);

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }
}
