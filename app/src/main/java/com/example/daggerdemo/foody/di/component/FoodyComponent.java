package com.example.daggerdemo.foody.di.component;

import com.bumptech.glide.Glide;
import com.example.daggerdemo.foody.data.rest.RetrofitApi;
import com.example.daggerdemo.foody.di.module.RestaurantRepository;
import com.example.daggerdemo.foody.di.scope.FoodyApplicationScope;
import com.example.daggerdemo.foody.di.module.ApplicationModule;
import com.example.daggerdemo.foody.di.module.GlideModule;
import com.example.daggerdemo.foody.util.ViewModelFactory;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@FoodyApplicationScope
@Component(modules = {ApplicationModule.class, GlideModule.class})
public interface FoodyComponent {

    RetrofitApi getApiService();

    RestaurantRepository getRepository();

    Glide getGlide(); // For custom glide

    ViewModelFactory getViewModelFactory();

}
