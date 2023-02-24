package com.erenalparslan.spacexapijava.service;

import com.erenalparslan.spacexapijava.model.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpacaeXApi {

    //https://api.spacexdata.com/v3/rockets
    @GET("v3/rockets")
    Call<List<Rocket>> getData();
}
