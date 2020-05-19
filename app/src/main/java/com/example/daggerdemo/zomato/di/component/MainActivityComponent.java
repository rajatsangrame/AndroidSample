package com.example.daggerdemo.zomato.di.component;

import com.example.daggerdemo.zomato.MainActivity;
import com.example.daggerdemo.zomato.di.scope.MainActivityScope;
import com.example.daggerdemo.zomato.di.module.MainActivityModule;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Component(modules = MainActivityModule.class, dependencies = FoodyComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}
