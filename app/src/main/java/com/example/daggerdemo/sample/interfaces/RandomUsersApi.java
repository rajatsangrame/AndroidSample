package com.example.daggerdemo.sample.interfaces;


import com.example.daggerdemo.sample.model.RandomUsers;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
public interface RandomUsersApi {

    @GET("api")
    Single<RandomUsers> getRandomUsers(@Query("results") int size);
}