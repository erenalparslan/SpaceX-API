package com.erenalparslan.spacexapijava.model;


import com.google.gson.annotations.SerializedName;

public class Capsule {

    @SerializedName("capsule_serial")
    public String capsule_serial;
    @SerializedName("details")
    public String details;
    @SerializedName("type")
    public String type;
    @SerializedName("status")
    public String status;


}

