package com.example.pro.restaurante_demo.Models.realm;

import io.realm.RealmObject;

/**
 * Created by pro on 29/02/16.
 */
public class ComidaCore extends RealmObject{

    private float precio;

    private String nombre;

    private String img;

    public float getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImg() {
        return img;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
