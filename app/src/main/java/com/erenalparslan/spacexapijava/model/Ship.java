package com.erenalparslan.spacexapijava.model;

import com.google.gson.annotations.SerializedName;

public class Ship {
    @SerializedName("ship_name")
    public String ship_name;
    @SerializedName("ship_type")
    public String ship_type;

}
