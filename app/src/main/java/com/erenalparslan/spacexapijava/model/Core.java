package com.erenalparslan.spacexapijava.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Core {
    @SerializedName("core_serial")
    public String core_serial;
    @SerializedName("details")
    public String details;
    @SerializedName("status")
    public String status;
    @PrimaryKey(autoGenerate = true)
    public int coreId=0;


}
