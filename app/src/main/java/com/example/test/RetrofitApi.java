package com.example.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rajat Sangrame on 18/10/19.
 * http://github.com/rajatsangrame
 */
public interface RetrofitApi {

    @GET("users")
    Call<UserResponse> getUsers(@Query("page") long page);

}
