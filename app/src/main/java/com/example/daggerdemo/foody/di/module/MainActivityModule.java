package com.example.daggerdemo.foody.di.module;

import com.example.daggerdemo.foody.ui.main.MainActivity;

import dagger.Module;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module()
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

//    @Provides
//    @MainActivityScope
//    public RestaurantAdapter getRestaurantAdapter() {
//        return new RestaurantAdapter(mainActivity);
//    }

}
