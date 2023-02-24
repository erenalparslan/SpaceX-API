package com.erenalparslan.spacexapijava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Ship;

import java.util.ArrayList;

public class ShipAdapter extends RecyclerView.Adapter<ShipAdapter.RowHolder> {
    ArrayList<Ship> shipArrayList;
    private String[] colors = {"#144272", "#205295"};
    public ShipAdapter(ArrayList<Ship> shipArrayList) {
        this.shipArrayList = shipArrayList;
    }

    @NonNull
    @Override
    public ShipAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ship_recycler_row, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShipAdapter.RowHolder holder, int position) {
        holder.bind(shipArrayList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return shipArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder{

        TextView shipName;
        TextView shipType;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(Ship ship, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]));
            shipName = itemView.findViewById(R.id.shipName);
            shipType=itemView.findViewById(R.id.shipType);
            shipName.setText(ship.ship_name);
            shipType.setText(ship.ship_type);




        }
    }
}
