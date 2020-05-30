package com.example.daggerdemo.foody.di.module;

import com.example.daggerdemo.foody.adapter.RestaurantAdapter;
import com.example.daggerdemo.foody.di.scope.MainActivityScope;
import com.example.daggerdemo.foody.ui.fragment.HomeFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module()
public class HomeFragmentModule {

    private final HomeFragment fragment;

    public HomeFragmentModule(HomeFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @MainActivityScope
    public RestaurantAdapter getRestaurantAdapter() {
        return new RestaurantAdapter(fragment);
    }

}
