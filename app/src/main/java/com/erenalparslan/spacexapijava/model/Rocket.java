package com.erenalparslan.spacexapijava.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Rocket {
    @SerializedName("description")
    public String description;
    @SerializedName("rocket_name")
    public String rocket_name;
    @SerializedName("active")
    public Boolean active;
    @SerializedName("country")
    public String country;
    @PrimaryKey(autoGenerate = true)
    public int rocketId=0;
}


