package com.erenalparslan.spacexapijava.service;

import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.model.Ship;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IShipApi {
    @GET("v3/ships")
    Call<List<Ship>> getData();
}
