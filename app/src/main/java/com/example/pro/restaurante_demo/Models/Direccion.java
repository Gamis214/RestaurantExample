package com.example.pro.restaurante_demo.Models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Created by pro on 16/02/16.
 */
public class Direccion {

    @Getter
    private String numeroDireccion;
    @Getter
    private String departamento;
    @Getter
    private String ciudad;
    @Getter
    private String telefono;

    public static final List<Direccion> DIRECCIONES = new ArrayList<Direccion>();

    public Direccion(String numeroDireccion, String departamento, String ciudad, String telefono) {
        this.numeroDireccion = numeroDireccion;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    static {
        DIRECCIONES.add(new Direccion("Cra 24 #2C-50", "Valle", "Cali", "3459821"));
        DIRECCIONES.add(new Direccion("Calle 100 Trans. 23", "Valle", "Cali", "4992600"));
        DIRECCIONES.add(new Direccion("Ave. 3ra N. #20-10", "Valle", "Cali", "4400725"));
    }
}
