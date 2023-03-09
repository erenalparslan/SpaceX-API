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

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.adapter.RocketAdapter;
import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.repository.RocketRepository;
import com.erenalparslan.spacexapijava.service.SpacaeXApi;
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




    private void observeRockets()  {
        rocketRepository = new RocketRepository(getContext());
        rocketRepository.getUsers().observe(getViewLifecycleOwner(), new Observer<List<Rocket>>() {
            @Override
            public void onChanged(List<Rocket> rockets) {
                if (rockets.isEmpty()) {
                    loadData();
                }
                rocketModel=(ArrayList<Rocket>)rockets;
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
                    rocketRepository = new RocketRepository(getContext());
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


    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
            ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 8) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull
                RecyclerView.ViewHolder target) {


            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            Collections.swap(rocketModel, fromPosition, toPosition);

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
                            rocketRepository.deleteRocket(rocketModel.get(position)); ;
                            break;
                        case ItemTouchHelper .LEFT:

                            break;



                    }

                }
            });




        }

    };


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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        observeRockets();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);










    }

}
   /*    public void goToCapsule(View view){
           NavDirections action =RocketFragmentDirections.actionRocketFragmentToCapsuleFragment();
           Navigation.findNavController(view).navigate(action);
       }*/

