package com.example.daggerdemo.foody.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.daggerdemo.foody.di.key.ViewModelKey;
import com.example.daggerdemo.foody.ui.main.RestaurantViewModel;
import com.example.daggerdemo.foody.util.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Rajat Sangrame on 24/5/20.
 * http://github.com/rajatsangrame
 */
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantViewModel.class)
    abstract ViewModel providePlayerViewModel(RestaurantViewModel restaurantViewModel);
}
