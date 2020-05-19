package com.example.daggerdemo.zomato.di.module;

import com.bumptech.glide.Glide;
import com.example.daggerdemo.zomato.MainActivity;
import com.example.daggerdemo.zomato.adapter.RestaurantAdapter;
import com.example.daggerdemo.zomato.di.scope.MainActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public RestaurantAdapter getRestaurantAdapter() {
        return new RestaurantAdapter(mainActivity);
    }
}
