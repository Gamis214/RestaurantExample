package com.example.pro.restaurante_demo.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.pro.restaurante_demo.Models.SeccionesMiCuenta;

import java.util.List;

/**
 * Created by pro on 16/02/16.
 */
public class AdaptadorSecciones extends FragmentStatePagerAdapter {

    private List<SeccionesMiCuenta> lstFragments;

    public AdaptadorSecciones(FragmentManager fm, List<SeccionesMiCuenta> lstFragments) {
        super(fm);
        this.lstFragments = lstFragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstFragments.get(position).getTitulo();
    }

    @Override
    public Fragment getItem(int position) {
        return lstFragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return lstFragments.size();
    }
}
