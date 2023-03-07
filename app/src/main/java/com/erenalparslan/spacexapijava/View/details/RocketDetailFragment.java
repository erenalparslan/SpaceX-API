package com.erenalparslan.spacexapijava.View.details;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Rocket;


public class RocketDetailFragment extends Fragment {

    static String name;
    static String descripton;
    static Boolean type;
    static String country;
    TextView props1;
    TextView props;
    TextView twName;
    TextView props2;


    public static RocketDetailFragment newInstance(Rocket rocket) {

        descripton = rocket.description;
        name = rocket.rocket_name;
        type = rocket.active;
        country = rocket.country;

        return new RocketDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rocket_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        props = view.findViewById(R.id.prop);
        twName = view.findViewById(R.id.name);
        props1 = view.findViewById(R.id.prop1);
        props2 = view.findViewById(R.id.prop2);

        twName.setText(name);

        props.setText(descripton);
// Metin rengini değiştirmek için yeni bir SpannableString oluşturun
        SpannableString spannableString1 = new SpannableString("Country = " + country);
        SpannableString spannableString2 = new SpannableString("Active = " + type.toString());

// Metnin belirli bir kısmını seçmek için başlangıç ve bitiş indekslerini kullanın
        int startIndex1 = 0;
        int endIndex1 = "Country".length();
        int startIndex2 = 0;
        int endIndex2 = "Active".length();

// Metnin belirli bir kısmının rengini değiştirin
        spannableString1.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// TextView'lerdeki metni yeni SpannableString'lerle değiştirin
        props1.setText(spannableString1);
        props2.setText(spannableString2);



        super.onViewCreated(view, savedInstanceState);
    }
}