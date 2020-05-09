package com.example.daggerdemo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RandomUsers{

    @SerializedName("results")
    private List<Result> results;

    @SerializedName("info")
    private Info info;

    public List<Result> getResults(){
        return results;
    }

    public Info getInfo(){
        return info;
    }
}