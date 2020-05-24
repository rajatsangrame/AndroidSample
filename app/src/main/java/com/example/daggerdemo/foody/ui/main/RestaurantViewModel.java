package com.example.daggerdemo.foody.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerdemo.foody.di.module.RestaurantRepository;
import com.example.daggerdemo.foody.data.model.RestaurantsItem;

import java.util.List;

import javax.inject.Inject;

public class RestaurantViewModel extends ViewModel {

    RestaurantRepository restaurantRepository;

    @Inject
    public RestaurantViewModel(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void getRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public LiveData<List<RestaurantsItem>> getAllRestaurants() {
        return restaurantRepository.getRestaurantList();
    }

}
