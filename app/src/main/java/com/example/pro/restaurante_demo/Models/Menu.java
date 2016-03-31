package com.example.pro.restaurante_demo.Models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by pro on 17/02/16.
 */
public class Menu {

    @Getter
    @Setter
    private List<Comida> POSTRES;

    @Getter
    @Setter
    private List<Comida> BEBIDAS;

    @Getter
    @Setter
    private List<Comida> PLATILLOS;

    @Getter
    @Setter
    private List<Comida> COMIDAS_POPULARES;

}
