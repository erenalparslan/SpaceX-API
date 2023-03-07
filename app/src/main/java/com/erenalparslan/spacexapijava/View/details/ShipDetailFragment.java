package com.erenalparslan.spacexapijava.View.details;

import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Ship;
import com.squareup.picasso.Picasso;


public class ShipDetailFragment extends Fragment {
    static String name;
    static String ship_type;
    static String imageurl;
    static String home_port;
    TextView props1;
    TextView props;
    TextView twName;
    ImageView image;
    Bitmap bitmap = null;

    public static ShipDetailFragment newInstance(Ship ship) {
        name = ship.ship_name;
        ship_type = ship.ship_type;
        imageurl = ship.url;
        home_port = ship.home_port;

        return new ShipDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        props = view.findViewById(R.id.prop);
        twName = view.findViewById(R.id.name);
        image = view.findViewById(R.id.imageView);
        props1 = view.findViewById(R.id.prop1);
        twName.setText(name);

        // Metin rengini değiştirmek için yeni bir SpannableString oluşturun
        SpannableString spannableString1 = new SpannableString("Ship Type = "+ship_type);
        SpannableString spannableString2 = new SpannableString("Home Port = "+home_port);

// Metnin belirli bir kısmını seçmek için başlangıç ve bitiş indekslerini kullanın
        int startIndex1 = 0;
        int endIndex1 = "Ship Type ".length();
        int startIndex2 = 0;
        int endIndex2 = "Home Port".length();

// Metnin belirli bir kısmının rengini değiştirin
        spannableString1.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString2.setSpan(new ForegroundColorSpan(Color.YELLOW), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// TextView'lerdeki metni yeni SpannableString'lerle değiştirin
        props.setText(spannableString1);
        props1.setText(spannableString2);


       if(imageurl==null){

       }else{
           Picasso.get().load(imageurl).into(image);
       }



        super.onViewCreated(view, savedInstanceState);
    }


}