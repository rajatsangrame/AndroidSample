package com.example.daggerdemo.foody.di.module;

import androidx.fragment.app.Fragment;

import com.example.daggerdemo.foody.adapter.RestaurantAdapter;
import com.example.daggerdemo.foody.di.scope.MainActivityScope;
import com.example.daggerdemo.foody.ui.main.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Module()
public class HomeFragmentModule {

    private final Fragment fragment;

    public HomeFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @MainActivityScope
    public RestaurantAdapter getRestaurantAdapter() {
        return new RestaurantAdapter(fragment);
    }

}
