package com.example.daggerdemo.foody.di.component;

import com.example.daggerdemo.foody.ui.main.MainActivity;
import com.example.daggerdemo.foody.di.scope.MainActivityScope;
import com.example.daggerdemo.foody.di.module.MainActivityModule;

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
