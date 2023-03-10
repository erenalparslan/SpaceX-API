package com.erenalparslan.spacexapijava.View.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.adapter.CapsuleAdapter;
import com.erenalparslan.spacexapijava.adapter.ShipAdapter;
import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Ship;
import com.erenalparslan.spacexapijava.repository.CapsuleRepository;
import com.erenalparslan.spacexapijava.repository.ShipRepository;
import com.erenalparslan.spacexapijava.service.IShipApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ShipFragment extends Fragment {
    public static ShipFragment newInstance() {
        return new ShipFragment();
    }


    ArrayList<Ship> ships;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    ShipAdapter shipAdapter;
    ShipRepository shipRepository;
    private Executor executor = Executors.newSingleThreadExecutor();

    private void observeShip() {
        shipRepository=new ShipRepository(getContext());
        shipRepository.getShip().observe(getViewLifecycleOwner(), new Observer<List<Ship>>() {
            @Override
            public void onChanged(List<Ship> ship) {
                if(ship.isEmpty()){
                    loadData();
                }
                ships=(ArrayList<Ship>)ship;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                shipAdapter = new ShipAdapter((ArrayList<Ship>) ship);
                recyclerView.setAdapter(shipAdapter);
            }
        });
    }


    public void loadData() {
        IShipApi iShipApi = retrofit.create(IShipApi.class);
        Call<List<Ship>> call = iShipApi.getData();
        call.enqueue(new Callback<List<Ship>>() {
            @Override
            public void onResponse(Call<List<Ship>> call, Response<List<Ship>> response) {
                if (response.isSuccessful()) {
                    List<Ship> responseList = response.body();
                    ships = new ArrayList<>(responseList);
                    shipRepository=new ShipRepository(getContext());
                    for (Ship ship : ships) {
                        executor.execute(new Runnable() { // Run the database operation on a separate thread
                            @Override
                            public void run() {
                                shipRepository.insertShip(ship);
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Ship>> call, Throwable t) {

            }
        });
    }



    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
            ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 8) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull
                RecyclerView.ViewHolder target) {


            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(ships, fromPosition, toPosition);

            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;


        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition() ;
            executor.execute(new Runnable() { // Run the database operation on a separate thread
                @Override
                public void run() {
                    switch (direction) {
                        case ItemTouchHelper . RIGHT:
                            shipRepository.deleteShip(ships.get(position)); ;
                            break;
                        case ItemTouchHelper .LEFT:

                            break;



                    }

                }
            });




        }

    };



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_ship, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerView = view.findViewById(R.id.shipRecyclerView);
        observeShip();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        super.onViewCreated(view, savedInstanceState);
    }
}