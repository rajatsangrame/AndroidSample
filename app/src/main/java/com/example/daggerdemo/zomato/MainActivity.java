package com.example.daggerdemo.zomato;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerdemo.R;
import com.example.daggerdemo.zomato.adapter.RestaurantAdapter;
import com.example.daggerdemo.zomato.di.component.DaggerMainActivityComponent;
import com.example.daggerdemo.zomato.di.component.MainActivityComponent;
import com.example.daggerdemo.zomato.di.module.MainActivityModule;
import com.example.daggerdemo.zomato.model.ApiResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Inject
    RestaurantAdapter adapter;

    @Inject
    RetrofitApi retrofitApi;

    MainActivityComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        component = DaggerMainActivityComponent
                .builder()
                .foodyComponent(FoodyApplication.get(this).getFoodyComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        component.injectMainActivity(this);

        makeNetworkCall();
    }

    private void makeNetworkCall() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        Call<ApiResponse> call = retrofitApi.search("chicken", 0, 10, 21.1471401, 79.0531879, 200, "");
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if (response.body() != null) {

                    adapter.setRestaurants(response.body().getRestaurants());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });
    }
}
