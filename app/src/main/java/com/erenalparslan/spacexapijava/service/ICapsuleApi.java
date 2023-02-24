package com.erenalparslan.spacexapijava.service;

import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICapsuleApi {

    //https://api.spacexdata.com/v3/rockets
    @GET("v3/capsules")
    Call<List<Capsule>> getData();
}
