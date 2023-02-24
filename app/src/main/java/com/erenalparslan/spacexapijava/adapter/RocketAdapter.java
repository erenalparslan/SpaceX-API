package com.erenalparslan.spacexapijava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Rocket;

import java.util.ArrayList;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.RowHolder> {

    private ArrayList<Rocket> rocketArrayList;
    private String[] colors = {"#144272", "#205295"};

    public RocketAdapter(ArrayList<Rocket> rocketArrayList) {
        this.rocketArrayList = rocketArrayList;
    }

    @NonNull
    @Override
    public RocketAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rockets_recycler_row, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RocketAdapter.RowHolder holder, int position) {
        holder.bind(rocketArrayList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return rocketArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {


        TextView rocket_name;
        TextView id;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Rocket rocket,String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]));
            rocket_name = itemView.findViewById(R.id.rocketName);
            id=itemView.findViewById(R.id.id);

            rocket_name.setText(rocket.rocket_name);
            id.setText(rocket.id);

        }
    }
}
