package com.erenalparslan.spacexapijava.model;

import com.google.gson.annotations.SerializedName;

public class Rocket{
    @SerializedName("id")
    public String id;
    @SerializedName("rocket_name")
     public String rocket_name;
    @SerializedName("active")
    public   Boolean active;
}

