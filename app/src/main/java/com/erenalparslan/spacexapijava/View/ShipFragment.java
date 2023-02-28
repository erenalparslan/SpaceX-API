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
import com.erenalparslan.spacexapijava.adapter.ShipAdapter;
import com.erenalparslan.spacexapijava.model.Ship;
import com.erenalparslan.spacexapijava.service.IShipApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShipFragment extends Fragment {
    public static ShipFragment  newInstance(){
        return new ShipFragment();
    }


    ArrayList<Ship> ships;
    private String base_url="https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson=new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    ShipAdapter shipAdapter;



    public void loadData(){
        IShipApi iShipApi=retrofit.create(IShipApi.class);
        Call<List<Ship>> call=iShipApi.getData();
        call.enqueue(new Callback<List<Ship>>() {
            @Override
            public void onResponse(Call<List<Ship>> call, Response<List<Ship>> response) {
                if(response.isSuccessful()){
                    List<Ship> responseList= response.body();
                    ships=new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    shipAdapter=new ShipAdapter(ships);
                    recyclerView.setAdapter(shipAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Ship>> call, Throwable t) {

            }
        });
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit =new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView=view.findViewById(R.id.shipRecyclerView);
        loadData();
        super.onViewCreated(view, savedInstanceState);
    }
}