package com.erenalparslan.spacexapijava.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Ship {
    @SerializedName("ship_name")
    public String ship_name;
    @SerializedName("ship_type")
    public String ship_type;
    @SerializedName("home_port")
    public String home_port;
    @SerializedName("image")
    public String url;
@PrimaryKey(autoGenerate = true)
    public int shipId=0;

    public Ship(String ship_name, String ship_type, String home_port, String url) {
        this.ship_name = ship_name;
        this.ship_type = ship_type;
        this.home_port = home_port;
        this.url = url;
    }
}
