package com.erenalparslan.spacexapijava.service;


import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Company;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICompanyApi {
    @GET("v3/info")
    Call<Company> getData();
}

