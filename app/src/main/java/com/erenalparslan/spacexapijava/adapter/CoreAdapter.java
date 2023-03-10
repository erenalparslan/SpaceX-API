package com.erenalparslan.spacexapijava.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.View.details.CoreDetailFragment;
import com.erenalparslan.spacexapijava.model.Core;

import java.util.ArrayList;

public class CoreAdapter extends RecyclerView.Adapter<CoreAdapter.RowHolder> {
    ArrayList<Core> coreArrayList;
    private String[] colors = {"#144272", "#205295"};

    public CoreAdapter(ArrayList<Core> coreArrayList) {
        this.coreArrayList = coreArrayList;
    }

    @NonNull
    @Override
    public CoreAdapter.RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.core_recycler_row, parent, false);

        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoreAdapter.RowHolder holder, int position) {
        holder.bind(coreArrayList.get(position), colors, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.frameLayout, CoreDetailFragment.newInstance(coreArrayList.get(holder.getAdapterPosition())));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return coreArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView serial_name;
        TextView details;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Core core, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]));
            serial_name = itemView.findViewById(R.id.capsuleName);
            details = itemView.findViewById(R.id.details);
            if (core.details == null) {
                core.details="No details found.";
            } else {
                details.setText(core.details);
            }

            serial_name.setText(core.core_serial);
            details.setText(core.details);

        }
    }
}