package com.example.pro.restaurante_demo.Models;

import android.support.v4.app.Fragment;

import lombok.Getter;

/**
 * Created by pro on 16/02/16.
 */
public class SeccionesMiCuenta {

    @Getter
    private Fragment fragment;

    @Getter
    private String Titulo;

    public SeccionesMiCuenta(Fragment fragment,String Titulo){
        this.fragment = fragment;
        this.Titulo = Titulo;
    }

}
