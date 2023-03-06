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
import com.erenalparslan.spacexapijava.adapter.CapsuleAdapter;
import com.erenalparslan.spacexapijava.model.Capsule;

import com.erenalparslan.spacexapijava.service.ICapsuleApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CapsuleFragment extends Fragment {

    public static CapsuleFragment newInstance() {
        return new CapsuleFragment();
    }

    ArrayList<Capsule> capsules;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    CapsuleAdapter capsuleAdapter;



    public void loadData() {
        ICapsuleApi iCapsuleApi = retrofit.create(ICapsuleApi.class);
        Call<List<Capsule>> call = iCapsuleApi.getData();
        call.enqueue(new Callback<List<Capsule>>() {
            @Override
            public void onResponse(Call<List<Capsule>> call, Response<List<Capsule>> response) {
                if (response.isSuccessful()) {
                    List<Capsule> responseList = response.body();
                    capsules = new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                    capsuleAdapter = new CapsuleAdapter(capsules);
                    recyclerView.setAdapter(capsuleAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Capsule>> call, Throwable t) {

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
        return inflater.inflate(R.layout.fragment_capsule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.capsuleRecyclerView);
        loadData();
        super.onViewCreated(view, savedInstanceState);
    }
}