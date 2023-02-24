package com.erenalparslan.spacexapijava.View;

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
import com.erenalparslan.spacexapijava.adapter.RocketAdapter;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.service.SpacaeXApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RocketFragment extends Fragment {


    public static RocketFragment newInstance() {
        return new RocketFragment();
    }

    ArrayList<Rocket> rocketModel;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    RocketAdapter recyclerViewAdapter;


    private void loadData() {
        SpacaeXApi spacaeXApi = retrofit.create(SpacaeXApi.class);
        Call<List<Rocket>> call = spacaeXApi.getData();
        call.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                if (response.isSuccessful()) {
                    List<Rocket> responseList = response.body();
                    rocketModel = new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerViewAdapter = new RocketAdapter(rocketModel);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }


            }

            @Override
            public void onFailure(Call<List<Rocket>> call, Throwable t) {

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
        return inflater.inflate(R.layout.fragment_rocket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerRocket);

        loadData();

    }


   /*    public void goToCapsule(View view){
           NavDirections action =RocketFragmentDirections.actionRocketFragmentToCapsuleFragment();
           Navigation.findNavController(view).navigate(action);
       }*/
}
