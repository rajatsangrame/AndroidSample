package com.example.daggerdemo.foody.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daggerdemo.R;
import com.example.daggerdemo.foody.FoodyApplication;
import com.example.daggerdemo.foody.adapter.RestaurantAdapter;
import com.example.daggerdemo.foody.di.component.DaggerMainActivityComponent;
import com.example.daggerdemo.foody.di.component.MainActivityComponent;
import com.example.daggerdemo.foody.di.module.MainActivityModule;
import com.example.daggerdemo.foody.di.module.RestaurantRepository;
import com.example.daggerdemo.foody.data.model.RestaurantsItem;
import com.example.daggerdemo.foody.util.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    RestaurantAdapter adapter;

    @Inject
    RestaurantRepository restaurantRepository;

    @Inject
    ViewModelFactory factory;

    RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent component = DaggerMainActivityComponent
                .builder()
                .foodyComponent(FoodyApplication.get(this).getFoodyComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        component.injectMainActivity(this);

        init();
    }

    private void init() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        restaurantViewModel = new ViewModelProvider(this, factory).get(RestaurantViewModel.class);

        restaurantViewModel.getAllRestaurants().observe(this, new Observer<List<RestaurantsItem>>() {
            @Override
            public void onChanged(List<RestaurantsItem> restaurantsItems) {
                adapter.setRestaurants(restaurantsItems);
            }
        });
    }
}
