package com.erenalparslan.spacexapijava.View.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erenalparslan.spacexapijava.R;
import com.erenalparslan.spacexapijava.model.Capsule;


public class CapsuleDetailFragment extends Fragment {
    static String name;
    static String details;
    static String type;
    static String status;
    TextView props1;
    TextView props;
    TextView twName;
    TextView props2;


    public static CapsuleDetailFragment newInstance(Capsule capsule) {
        name = capsule.capsule_serial;
        details = capsule.details;
        type = capsule.type;
        status = capsule.status;


        return new CapsuleDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_capsule_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        props = view.findViewById(R.id.prop);
        twName = view.findViewById(R.id.name);
        props1 = view.findViewById(R.id.prop1);
        props2 = view.findViewById(R.id.prop2);
        props.setText(details);
        twName.setText(name);
        props1.setText("Type = "+type);
        props2.setText("Status = "+status);

        super.onViewCreated(view, savedInstanceState);
    }
}