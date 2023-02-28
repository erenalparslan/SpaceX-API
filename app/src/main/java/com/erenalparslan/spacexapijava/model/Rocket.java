package com.erenalparslan.spacexapijava.model;

import com.google.gson.annotations.SerializedName;

public class Rocket {
    @SerializedName("description")
    public String description;
    @SerializedName("rocket_name")
    public String rocket_name;
    @SerializedName("active")
    public Boolean active;
    @SerializedName("country")
    public String country;
}

