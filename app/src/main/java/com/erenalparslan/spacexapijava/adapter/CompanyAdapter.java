package com.erenalparslan.spacexapijava.adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Capsule;
import com.erenalparslan.spacexapijava.model.Company;
import com.squareup.picasso.Picasso;

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


            // Metin rengini değiştirmek için yeni bir SpannableString oluşturun
            SpannableString spannableString1 = new SpannableString("Founder : " + company.founder);
            SpannableString spannableString2 = new SpannableString("Founded : " + company.founded);
            SpannableString spannableString3 = new SpannableString("Employees : " + company.employees);
            SpannableString spannableString4 = new SpannableString("CEO : " + company.ceo);
            SpannableString spannableString5 = new SpannableString("COO : " + company.coo);
            SpannableString spannableString6 = new SpannableString("CTO : " + company.cto_propulsion);
            SpannableString spannableString7 = new SpannableString("Valuation : " + company.valuation);


// Metnin belirli bir kısmını seçmek için başlangıç ve bitiş indekslerini kullanın
            int startIndex1 = 0;
            int endIndex1 = "Founder : ".length();
            int startIndex2 = 0;
            int endIndex2 = "Founded : ".length();

            int startIndex3 = 0;
            int endIndex3 = "Employees : ".length();
            int startIndex4 = 0;
            int endIndex4 = "CEO : ".length();

            int startIndex5 = 0;
            int endIndex5 = "Valuation : ".length();


// Metnin belirli bir kısmının rengini değiştirin
            spannableString1.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString2.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString3.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex3, endIndex3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString4.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex4, endIndex4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString5.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex4, endIndex4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString6.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex4, endIndex4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString7.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex5, endIndex5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


// TextView'lerdeki metni yeni SpannableString'lerle değiştirin
            name.setText(company.name);
            founder.setText(spannableString1);
            founded.setText(spannableString2);
            employess.setText(spannableString3);
            ceo.setText(spannableString4);
            coo.setText(spannableString5);
            cto_propulsion.setText(spannableString6);
            valuation.setText(spannableString7);





        }
    }
}
