package com.erenalparslan.spacexapijava.View.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.adapter.CompanyAdapter;
import com.erenalparslan.spacexapijava.adapter.ShipAdapter;
import com.erenalparslan.spacexapijava.model.Company;
import com.erenalparslan.spacexapijava.model.Ship;
import com.erenalparslan.spacexapijava.repository.CompanyRepository;
import com.erenalparslan.spacexapijava.repository.ShipRepository;
import com.erenalparslan.spacexapijava.service.ICompanyApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
    CompanyRepository companyRepository;
    private Executor executor = Executors.newSingleThreadExecutor();


    private void observerCompany() {
        companyRepository=new CompanyRepository(getContext());
        companyRepository.getCompany().observe(getViewLifecycleOwner(), new Observer<List<Company>>() {
            @Override
            public void onChanged(List<Company> companies) {
                if(companies.size()==0){
                    System.out.println(companies.size());
                    loadData();
                }
                System.out.println(companies.get(0));
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                System.out.println(companies.size());
                companyAdapter = new CompanyAdapter(companies.get(0));
                recyclerView.setAdapter(companyAdapter);
            }
        });
    }



    public void loadData() {
        ICompanyApi iCompanyApi = retrofit.create(ICompanyApi.class);
        Call<Company> call = iCompanyApi.getData();
        call.enqueue(new Callback<Company>() {

            @Override
            public void onResponse(Call<Company> call, Response<Company> response) {
                if (response.isSuccessful()) {
                    Company company = response.body();
                    companies = company;
                    companyRepository=new CompanyRepository(getContext());
                    System.out.println(companies.name);

                        executor.execute(new Runnable() { // Run the database operation on a separate thread
                            @Override
                            public void run() {
                                companyRepository.insertCompany(companies);

                            }

                        });

                    observerCompany();
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