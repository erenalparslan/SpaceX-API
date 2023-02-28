package com.erenalparslan.spacexapijava.View.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.adapter.CompanyAdapter;
import com.erenalparslan.spacexapijava.model.Company;
import com.erenalparslan.spacexapijava.service.ICompanyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CompanyFragment extends Fragment {


    public static CompanyFragment newInstance() {

        return new CompanyFragment();
    }

    Company companies;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    CompanyAdapter companyAdapter;

    public void loadData() {
        ICompanyApi iCompanyApi = retrofit.create(ICompanyApi.class);
        Call<Company> call = iCompanyApi.getData();
        call.enqueue(new Callback<Company>() {

            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                if (response.isSuccessful()) {
                    Company company = response.body();
                    companies = company;
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    companyAdapter = new CompanyAdapter(companies);
                    recyclerView.setAdapter(companyAdapter);

                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.companyRecycler);
        loadData();
        super.onViewCreated(view, savedInstanceState);
    }
}