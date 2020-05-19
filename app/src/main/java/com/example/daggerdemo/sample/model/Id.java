package com.example.daggerdemo.sample.model;

import com.google.gson.annotations.SerializedName;

public class Id{

    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private String value;

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }
}