package com.example.test;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajat Sangrame on 19/10/19.
 * http://github.com/rajatsangrame
 */
public class UserDataSource extends PageKeyedDataSource<Long, User> {

    public static int PAGE_SIZE = 6;
    public static long FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Long> params,
                            @NonNull final LoadInitialCallback<Long, User> callback) {


        RetrofitApi retrofitApi = RetrofitClient.buildService(RetrofitApi.class);
        Call<UserResponse> call = retrofitApi.getUsers(FIRST_PAGE);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, null, FIRST_PAGE + 1);
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }

        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params,
                           @NonNull final LoadCallback<Long, User> callback) {

        RetrofitApi retrofitApi = RetrofitClient.buildService(RetrofitApi.class);
        Call<UserResponse> call = retrofitApi.getUsers(params.key);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(responseItems, key);
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, User> callback) {

        RetrofitApi retrofitApi = RetrofitClient.buildService(RetrofitApi.class);
        Call<UserResponse> call = retrofitApi.getUsers(params.key);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                UserResponse apiResponse = response.body();
                if (apiResponse != null) {
                    List<User> responseItems = apiResponse.getUsers();
                    callback.onResult(responseItems, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
