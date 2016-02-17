package com.example.pro.restaurante_demo.Fragments.Categorias;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
public class FragmentoCategorias extends Fragment{

    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);
        }

        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);

        tabLayout = new TabLayout(getActivity());
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBarLayout.addView(tabLayout);
    }

    private void poblarViewPager(ViewPager viewPager) {

        List<SeccionesMiCuenta> lstFragments = new ArrayList<>();
        SeccionesMiCuenta s1 = new SeccionesMiCuenta(FragmentoCategoria.nuevaInstancia(0),getString(R.string.titulo_tab_platillos));
        SeccionesMiCuenta s2 = new SeccionesMiCuenta(FragmentoCategoria.nuevaInstancia(1),getString(R.string.titulo_tab_bebidas));
        SeccionesMiCuenta s3 = new SeccionesMiCuenta(FragmentoCategoria.nuevaInstancia(2),getString(R.string.titulo_tab_postres));
        lstFragments.add(s1);
        lstFragments.add(s2);
        lstFragments.add(s3);

        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager(),lstFragments);

        viewPager.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBarLayout.removeView(tabLayout);
    }

}
