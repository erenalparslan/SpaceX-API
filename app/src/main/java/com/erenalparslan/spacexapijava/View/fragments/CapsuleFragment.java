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
import com.erenalparslan.spacexapijava.adapter.RocketAdapter;
import com.erenalparslan.spacexapijava.model.Capsule;

import com.erenalparslan.spacexapijava.model.Rocket;
import com.erenalparslan.spacexapijava.repository.CapsuleRepository;
import com.erenalparslan.spacexapijava.repository.RocketRepository;
import com.erenalparslan.spacexapijava.service.ICapsuleApi;
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


public class CapsuleFragment extends Fragment {

    public static CapsuleFragment newInstance() {
        return new CapsuleFragment();
    }
    CapsuleRepository capsuleRepository;
    ArrayList<Capsule> capsules;
    private String base_url = "https://api.spacexdata.com/";
    Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    RecyclerView recyclerView;
    CapsuleAdapter capsuleAdapter;
    private Executor executor = Executors.newSingleThreadExecutor();

    private void observeCapsule() {
        capsuleRepository=new CapsuleRepository(getContext());
        capsuleRepository.getCpsule().observe(getViewLifecycleOwner(), new Observer<List<Capsule>>() {
            @Override
            public void onChanged(List<Capsule> capsul) {
                if(capsul.isEmpty()){
                    loadData();
                }
                capsules=(ArrayList<Capsule>)capsul;
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                capsuleAdapter = new CapsuleAdapter((ArrayList<Capsule>) capsul);
                recyclerView.setAdapter(capsuleAdapter);
            }
        });
    }



    public void loadData() {
        ICapsuleApi iCapsuleApi = retrofit.create(ICapsuleApi.class);
        Call<List<Capsule>> call = iCapsuleApi.getData();
        call.enqueue(new Callback<List<Capsule>>() {
            @Override
            public void onResponse(Call<List<Capsule>> call, Response<List<Capsule>> response) {
                if (response.isSuccessful()) {
                    List<Capsule> responseList = response.body();
                    capsules = new ArrayList<>(responseList);
                    capsuleRepository=new CapsuleRepository(getContext());
                    for (Capsule capsule : capsules) {
                        executor.execute(new Runnable() { // Run the database operation on a separate thread
                            @Override
                            public void run() {
                                capsuleRepository.insertCapsule(capsule);
                            }
                        });
                    }


                }
            }

            @Override
            public void onFailure(Call<List<Capsule>> call, Throwable t) {

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

            Collections.swap(capsules, fromPosition, toPosition);

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
                            capsuleRepository.deleteCapsule(capsules.get(position)); ;
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
        return inflater.inflate(R.layout.fragment_capsule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.capsuleRecyclerView);
        observeCapsule();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        super.onViewCreated(view, savedInstanceState);
    }
}