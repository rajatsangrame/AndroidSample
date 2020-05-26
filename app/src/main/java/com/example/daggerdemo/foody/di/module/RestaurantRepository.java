package com.example.daggerdemo.foody.di.module;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.daggerdemo.foody.data.rest.RetrofitApi;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationContext;
import com.example.daggerdemo.foody.data.model.ApiResponse;
import com.example.daggerdemo.foody.data.model.RestaurantsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajat Sangrame on 23/5/20.
 * http://github.com/rajatsangrame
 */

public class RestaurantRepository {

    RetrofitApi retrofitApi;
    MutableLiveData<List<RestaurantsItem>> restaurantsItems;

    public RestaurantRepository(@FoodyApplicationContext Context context, RetrofitApi retrofitApi) {
        this.retrofitApi = retrofitApi;
        restaurantsItems = new MutableLiveData<>();
        fetchData();
    }

    public MutableLiveData<List<RestaurantsItem>> getRestaurantList() {
        return restaurantsItems;
    }

    private void fetchData() {
        Call<ApiResponse> call = retrofitApi.search("chicken", 0, 10, 21.1471401, 79.0531879, 200, "");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (response.body() != null) {
                    restaurantsItems.setValue(response.body().getRestaurants());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
            }
        });
    }
}
