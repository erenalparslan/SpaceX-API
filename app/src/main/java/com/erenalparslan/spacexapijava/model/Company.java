package com.erenalparslan.spacexapijava.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity
public class Company {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("founder")
    public String founder;
    @SerializedName("founded")
    public String founded;
    @SerializedName("employees")
    public String employees;
    @SerializedName("ceo")
    public String ceo;
    @SerializedName("coo")
    public String coo;
    @SerializedName("cto_propulsion")
    public String cto_propulsion;
    @SerializedName("valuation")
    public String valuation;

    @PrimaryKey(autoGenerate = true)
    public int companyId=0;

}
