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

    public Rocket(String description, String rocket_name, Boolean active, String country) {
        this.description = description;
        this.rocket_name = rocket_name;
        this.active = active;
        this.country = country;
    }

    @PrimaryKey(autoGenerate = true)
    public int rocketId=0;


}


