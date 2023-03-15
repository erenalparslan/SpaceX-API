package com.erenalparslan.spacexapijava.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Capsule {

    @SerializedName("capsule_serial")
    public String capsule_serial;
    @SerializedName("details")
    public String details;
    @SerializedName("type")
    public String type;
    @SerializedName("status")
    public String status;

    @PrimaryKey(autoGenerate = true)
    public int capsuleid=0;

    public Capsule(String capsule_serial, String details, String type, String status) {
        this.capsule_serial = capsule_serial;
        this.details = details;
        this.type = type;
        this.status = status;
    }
}

