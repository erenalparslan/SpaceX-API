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
import com.erenalparslan.spacexapijava.adapter.RocketAdapter;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.repository.RocketRepository;
import com.erenalparslan.spacexapijava.service.SpacaeXApi;
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
    RocketRepository rocketRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

  /*  private void loadRoom() {
        SpacaeXApi spacaeXApi = retrofit.create(SpacaeXApi.class);
        Call<List<Rocket>> call = spacaeXApi.getData();
        call.enqueue(new Callback<List<Rocket>>() {

            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                if (response.isSuccessful()) {
                    List<Rocket> responseList = response.body();
                    rocketModel = new ArrayList<>(responseList);
                    for (Rocket rocket : rocketModel) {
                        rocketRepository.insertRocket(rocket);
                    }


                }

            }

            @Override
            public void onFailure(Call<List<Rocket>> call, Throwable t) {

            }
        });
    }*/

    private void observeRockets() {
        rocketRepository=new RocketRepository(getContext());
        rocketRepository.getUsers().observe(getViewLifecycleOwner(), new Observer<List<Rocket>>() {
            @Override
            public void onChanged(List<Rocket> rockets) {
                if(rockets.isEmpty()){
                 loadData();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerViewAdapter = new RocketAdapter((ArrayList<Rocket>) rockets);
                recyclerView.setAdapter(recyclerViewAdapter);
            }
        });
    }


    private void loadData() {
        SpacaeXApi spacaeXApi = retrofit.create(SpacaeXApi.class);
        Call<List<Rocket>> call = spacaeXApi.getData();
        call.enqueue(new Callback<List<Rocket>>() {
            @Override
            public void onResponse(Call<List<Rocket>> call, Response<List<Rocket>> response) {
                if (response.isSuccessful()) {
                    List<Rocket> responseList = response.body();
                    rocketModel = new ArrayList<>(responseList);
                    rocketRepository=new RocketRepository(getContext());
                    for (Rocket rocket : rocketModel) {
                        executor.execute(new Runnable() { // Run the database operation on a separate thread
                            @Override
                            public void run() {
                                rocketRepository.insertRocket(rocket);
                            }
                        });
                    }


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

        observeRockets();



    }


   /*    public void goToCapsule(View view){
           NavDirections action =RocketFragmentDirections.actionRocketFragmentToCapsuleFragment();
           Navigation.findNavController(view).navigate(action);
       }*/
}
