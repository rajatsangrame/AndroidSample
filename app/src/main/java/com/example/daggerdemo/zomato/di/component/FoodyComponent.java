package com.example.daggerdemo.zomato.di.component;

import com.bumptech.glide.Glide;
import com.example.daggerdemo.zomato.RetrofitApi;
import com.example.daggerdemo.zomato.di.scope.FoodyApplicationScope;
import com.example.daggerdemo.zomato.di.module.ApiResponseModule;
import com.example.daggerdemo.zomato.di.module.GlideModule;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@FoodyApplicationScope
@Component(modules = {ApiResponseModule.class, GlideModule.class})
public interface FoodyComponent {

    RetrofitApi getApiService();

    Glide getGlide(); // For custom glide
}
