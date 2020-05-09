package com.example.daggerdemo.component;

import com.example.daggerdemo.MainActivity;
import com.example.daggerdemo.adapter.RandomUserAdapter;
import com.example.daggerdemo.interfaces.MainActivityScope;
import com.example.daggerdemo.interfaces.RandomUsersApi;
import com.example.daggerdemo.module.MainActivityModule;

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
