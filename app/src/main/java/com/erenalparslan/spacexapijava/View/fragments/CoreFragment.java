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

import com.erenalparslan.spacexapijava.adapter.CoreAdapter;


import com.erenalparslan.spacexapijava.model.Core;

import com.erenalparslan.spacexapijava.model.Ship;
import com.erenalparslan.spacexapijava.repository.CoreRepository;
import com.erenalparslan.spacexapijava.service.ICoreApi;
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


public class CoreFragment extends Fragment {


    public static CoreFragment newInstance() {

        return new CoreFragment();
    }

    ArrayList<Core> core;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    CoreAdapter coreAdapter;
    CoreRepository coreRepository;
    private Executor executor = Executors.newSingleThreadExecutor();


    private void observeCore() {
        coreRepository=new CoreRepository(getContext());
        coreRepository.getCore().observe(getViewLifecycleOwner(), new Observer<List<Core>>() {
            @Override
            public void onChanged(List<Core> Cores) {
                if(Cores.isEmpty()){
                    loadData();
                }
                core=(ArrayList<Core>)Cores;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                coreAdapter = new CoreAdapter((ArrayList<Core>) Cores );
                recyclerView.setAdapter(coreAdapter);
            }
        });
    }


    public void loadData() {
        ICoreApi iCoreApi = retrofit.create(ICoreApi.class);
        Call<List<Core>> call = iCoreApi.getData();
        call.enqueue(new Callback<List<Core>>() {
            @Override
            public void onResponse(Call<List<Core>> call, Response<List<Core>> response) {
                if (response.isSuccessful()) {
                    List<Core> responseList = response.body();
                    core = new ArrayList<>(responseList);
                    coreRepository=new CoreRepository(getContext());
                    for (Core core : core) {
                        executor.execute(new Runnable() { // Run the database operation on a separate thread
                            @Override
                            public void run() {
                                coreRepository.insertCore(core);
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Core>> call, Throwable t) {

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

            Collections.swap(core, fromPosition, toPosition);

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
                            coreRepository.deleteCore(core.get(position)); ;
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
        return inflater.inflate(R.layout.fragment_core, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerCore);
         observeCore();

       DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        super.onViewCreated(view, savedInstanceState);
    }
}