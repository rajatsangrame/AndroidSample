package com.example.daggerdemo.foody.data.rest;


import com.example.daggerdemo.foody.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rajat Sangrame on 22/4/20.
 * http://github.com/rajatsangrame
 */
public interface RetrofitApi {

    @GET("search")
    Call<ApiResponse> search(@Query("q") String query,
                             @Query("start") int start,
                             @Query("count") int count,
                             @Query("lat") double lat,
                             @Query("lon") double lon,
                             @Query("radius") double radius,
                             @Query("cuisines") String cuisinesIds);

}
