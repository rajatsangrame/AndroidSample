package com.example.daggerdemo.sample.component;

import com.example.daggerdemo.sample.MainActivity;
import com.example.daggerdemo.sample.interfaces.MainActivityScope;
import com.example.daggerdemo.sample.module.MainActivityModule;

import dagger.Component;

/**
 * Created by Rajat Sangrame on 9/5/20.
 * http://github.com/rajatsangrame
 */
@Component(modules = MainActivityModule.class, dependencies = RandomUserComponent.class)
@MainActivityScope
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

}