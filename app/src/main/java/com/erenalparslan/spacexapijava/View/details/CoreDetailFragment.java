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
import com.erenalparslan.spacexapijava.model.Core;


public class CoreDetailFragment extends Fragment {
    static String name;
    static String details;
    static String status;
    TextView props1;
    TextView props;
    TextView twName;


    public static CoreDetailFragment newInstance(Core core) {
        name = core.core_serial;
        details = core.details;
        status = core.status;


        return new CoreDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_core_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        props = view.findViewById(R.id.prop);
        twName = view.findViewById(R.id.name);
        props1 = view.findViewById(R.id.prop1);

        props.setText(details);
        twName.setText(name);

        // Metin rengini değiştirmek için yeni bir SpannableString oluşturun
        SpannableString spannableString1 = new SpannableString("Status = "+status);

// Metnin belirli bir kısmını seçmek için başlangıç ve bitiş indekslerini kullanın
        int startIndex1 = 0;
        int endIndex1 = "Status =".length();


// Metnin belirli bir kısmının rengini değiştirin
        spannableString1.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// TextView'lerdeki metni yeni SpannableString'lerle değiştirin
        props1.setText(spannableString1);



        super.onViewCreated(view, savedInstanceState);

    }
}