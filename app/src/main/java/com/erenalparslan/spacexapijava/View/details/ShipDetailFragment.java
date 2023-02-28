package com.erenalparslan.spacexapijava.View.details;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
        props.setText("Ship Type ="+ship_type);
        twName.setText(name);
        props1.setText("Home Port = "+home_port);


        Picasso.get().load(imageurl).into(image);

        super.onViewCreated(view, savedInstanceState);
    }


}