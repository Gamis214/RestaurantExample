package com.example.pro.restaurante_demo.Servicios;

import com.example.pro.restaurante_demo.Models.ComidasService;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by pro on 17/02/16.
 */
public interface menuServiceAPI {

    @GET("/api/json/get/bVXMyHYmWa?indent=2")
    public void getComidasService(Callback<ComidasService> response);

}
