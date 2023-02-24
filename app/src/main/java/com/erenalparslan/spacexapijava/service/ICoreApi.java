package com.erenalparslan.spacexapijava.service;


import com.erenalparslan.spacexapijava.model.Core;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICoreApi {
    @GET("v3/cores")
    Call<List<Core>> getData();
}
