package com.erenalparslan.spacexapijava.adapter;

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
import com.erenalparslan.spacexapijava.View.details.CapsuleDetailFragment;
import com.erenalparslan.spacexapijava.model.Capsule;


import java.util.ArrayList;

public class CapsuleAdapter extends RecyclerView.Adapter<CapsuleAdapter.RowHolder> {

    ArrayList<Capsule> capsuleArrayList;

    private String[] colors = {"#144272", "#205295"};

    public CapsuleAdapter(ArrayList<Capsule> capsuleArrayList) {
        this.capsuleArrayList = capsuleArrayList;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.capsule_recycler_row, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(capsuleArrayList.get(position), colors, position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) v.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.frameLayout, CapsuleDetailFragment.newInstance(capsuleArrayList.get(holder.getAdapterPosition())));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return capsuleArrayList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView serial_name;
        TextView details;

        public RowHolder(@NonNull View itemView) {


            super(itemView);
        }

        public void bind(Capsule capsule, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]));
            serial_name = itemView.findViewById(R.id.capsuleName);
            details = itemView.findViewById(R.id.details);
            if (capsule.details == null) {
               capsule.details="No details found.";
            } else {
                details.setText(capsule.details);
            }
            serial_name.setText(capsule.capsule_serial);



        }
    }


}
