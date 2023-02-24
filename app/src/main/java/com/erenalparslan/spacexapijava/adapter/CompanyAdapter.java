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
import com.erenalparslan.spacexapijava.model.Company;

import java.util.ArrayList;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.RowHolder> {

    Company companyArrayList;
    private String[] colors = {"#144272", "#205295"};

    public CompanyAdapter(Company companyArrayList) {
        this.companyArrayList = companyArrayList;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.company_row, parent, false);

        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(companyArrayList, colors, position);
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class RowHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView founder;
        TextView founded;
        TextView employess;
        TextView ceo;
        TextView coo;
        TextView cto_propulsion;
        TextView valuation;


        public RowHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Company company, String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]));
            name = itemView.findViewById(R.id.name);
            founder = itemView.findViewById(R.id.founder);
            founded = itemView.findViewById(R.id.founded);
            employess = itemView.findViewById(R.id.employess);
            ceo = itemView.findViewById(R.id.ceo);
            coo = itemView.findViewById(R.id.coo);
            cto_propulsion = itemView.findViewById(R.id.cto);
            valuation = itemView.findViewById(R.id.valuation);

            name.setText(company.name);
            founder.setText("Founder : " + company.founder);
            founded.setText("Founded : " + company.founded);
            employess.setText("Employees : " + company.employees);
            ceo.setText("CEO : " + company.ceo);
            coo.setText("COO : " + company.coo);
            cto_propulsion.setText("CTO : " + company.cto_propulsion);
            valuation.setText("Valuation : " + company.valuation);

        }
    }
}
