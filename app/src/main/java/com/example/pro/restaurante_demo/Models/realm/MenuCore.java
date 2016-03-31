package com.example.pro.restaurante_demo.Models.realm;

import com.example.pro.restaurante_demo.Models.Comida;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by pro on 29/02/16.
 */
public class MenuCore extends RealmObject{

    private RealmList<ComidaCore> POSTRES;

    private RealmList<ComidaCore> BEBIDAS;

    private RealmList<ComidaCore> PLATILLOS;

    private RealmList<ComidaCore> COMIDAS_POPULARES;

    public RealmList<ComidaCore> getPOSTRES() {
        return POSTRES;
    }

    public RealmList<ComidaCore> getBEBIDAS() {
        return BEBIDAS;
    }

    public RealmList<ComidaCore> getPLATILLOS() {
        return PLATILLOS;
    }

    public RealmList<ComidaCore> getCOMIDAS_POPULARES() {
        return COMIDAS_POPULARES;
    }

    public void setPOSTRES(RealmList<ComidaCore> POSTRES) {
        this.POSTRES = POSTRES;
    }

    public void setBEBIDAS(RealmList<ComidaCore> BEBIDAS) {
        this.BEBIDAS = BEBIDAS;
    }

    public void setPLATILLOS(RealmList<ComidaCore> PLATILLOS) {
        this.PLATILLOS = PLATILLOS;
    }

    public void setCOMIDAS_POPULARES(RealmList<ComidaCore> COMIDAS_POPULARES) {
        this.COMIDAS_POPULARES = COMIDAS_POPULARES;
    }
}


