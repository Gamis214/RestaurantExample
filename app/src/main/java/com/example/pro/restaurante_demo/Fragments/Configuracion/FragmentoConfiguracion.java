package com.example.pro.restaurante_demo.Fragments.Configuracion;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.pro.restaurante_demo.R;

/**
 * Created by pro on 16/02/16.
 */
public class FragmentoConfiguracion extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }

}
